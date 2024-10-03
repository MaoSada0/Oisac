package ru.qq.database.serivce.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import ru.qq.common.payload.TeacherPayload;
import ru.qq.common.payload.TaskCatalogPayload;
import ru.qq.database.mongo_db.document.Task;
import ru.qq.database.mongo_db.document.TaskCatalog;
import ru.qq.database.mongo_db.document.Teacher;
import ru.qq.database.exception.TaskCatalogWithNameAlreadyExistsException;
import ru.qq.database.exception.TeacherAlreadyExistsException;
import ru.qq.database.exception.TeacherNotFoundException;
import ru.qq.database.mongo_db.repository.MongoDbRepository;
import ru.qq.database.serivce.MainService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j
public class MainServiceImpl implements MainService {

    private final MongoDbRepository mongoDbRepository;

    @Override
    public void saveTaskCatalog(String nameOfTeacher, TaskCatalogPayload catalogPayload) {
        if(!mongoDbRepository.existsById(nameOfTeacher))
            throw new TeacherNotFoundException("Teacher: {" + nameOfTeacher + "} not found in db");

        if(mongoDbRepository.existsByIdAndTaskCatalogName(nameOfTeacher, catalogPayload.name()))
            throw new TaskCatalogWithNameAlreadyExistsException("Task catalog: {" + catalogPayload.name() + "} already exists");

        Teacher teacher = mongoDbRepository.findById(nameOfTeacher).get();

        List<Task> tasks = catalogPayload.tasks().stream()
                .map(tp -> Task.builder()
                        .description(tp.description())
                        .answer(tp.answer())
                        .build())
                .collect(Collectors.toList());

        TaskCatalog newCatalog = TaskCatalog.builder()
                .name(catalogPayload.name())
                .tasks(tasks)
                .build();

        teacher.getTaskCatalogs().add(newCatalog);

        mongoDbRepository.save(teacher);
    }

    @Override
    public boolean existsTeacher(String nameOfTeacher) {
        return mongoDbRepository.existsById(nameOfTeacher);

    }

    @Override
    public void createTeacher(TeacherPayload teacherPayload) {
        if(mongoDbRepository.existsById(teacherPayload.name()))
            throw new TeacherAlreadyExistsException("Teacher: {" + teacherPayload.name() + "} already exists");

        Teacher teacher = Teacher.builder()
                            .nameOfTeacher(teacherPayload.name())
                            .taskCatalogs(new ArrayList<>())
                            .build();

        mongoDbRepository.save(teacher);
    }

    @Override
    public String[] getNamesOfTaskCatalogs(String nameOfTeacher) {
        if (!mongoDbRepository.existsById(nameOfTeacher))
            throw new TeacherNotFoundException("Teacher: {" + nameOfTeacher + "} not found in db");

        return mongoDbRepository.findTaskCatalogNamesByTeacherId(nameOfTeacher).toArray(new String[0]);

    }
}
