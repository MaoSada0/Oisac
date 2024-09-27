package ru.qq.database.mongo_db.serivce.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.qq.common.payload.GetTeacherPayload;
import ru.qq.common.payload.TaskCatalogPayload;
import ru.qq.common.payload.TaskPayload;
import ru.qq.database.mongo_db.document.Task;
import ru.qq.database.mongo_db.document.TaskCatalog;
import ru.qq.database.mongo_db.document.Teacher;
import ru.qq.database.mongo_db.repository.MongoDbRepository;
import ru.qq.database.mongo_db.serivce.MainService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    private final MongoDbRepository mongoDbRepository;

    @Override
    public boolean saveTaskCatalog(String nameOfTeacher, TaskCatalogPayload catalogPayload) {
        Optional<Teacher> teacherOptional = mongoDbRepository.findById(nameOfTeacher);



        if (teacherOptional.isPresent()) {
            Teacher teacher = teacherOptional.get();

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

            return true;
        }

        return false;
    }

    @Override
    public boolean existsTeacher(String id) {
        return mongoDbRepository.findById(id).isPresent();
    }

    @Override
    public boolean createTeacher(GetTeacherPayload teacherPayload) {
        if(this.existsTeacher(teacherPayload.name()))
            return false;

        Teacher teacher = Teacher.builder()
                            .nameOfTeacher(teacherPayload.name())
                            .taskCatalogs(new ArrayList<>())
                            .build();

        mongoDbRepository.save(teacher);

        return true;
    }
}
