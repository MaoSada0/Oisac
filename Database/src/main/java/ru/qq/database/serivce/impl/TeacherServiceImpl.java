package ru.qq.database.serivce.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.qq.common.payload.TeacherPayload;
import ru.qq.common.payload.TaskCatalogPayload;
import ru.qq.database.mongo_db.document.TaskMongo;
import ru.qq.database.mongo_db.document.TaskCatalogMongo;
import ru.qq.database.mongo_db.document.TeacherMongo;
import ru.qq.database.exception.teacher.TaskCatalogWithNameAlreadyExistsException;
import ru.qq.database.exception.teacher.TeacherAlreadyExistsException;
import ru.qq.database.exception.teacher.TeacherNotFoundException;
import ru.qq.database.mongo_db.repository.MongoTeacherRepository;
import ru.qq.database.postgres_db.entity.TeacherPostgres;
import ru.qq.database.postgres_db.repository.PostgresTeacherRepository;
import ru.qq.database.serivce.TeacherService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j
public class TeacherServiceImpl implements TeacherService {

    private final MongoTeacherRepository mongoTeacherRepository;
    private final PostgresTeacherRepository postgresTeacherRepository;

    @Override
    public void saveTaskCatalog(String nameOfTeacher, TaskCatalogPayload catalogPayload) {
        log.debug(postgresTeacherRepository.existsByTeacherNickname(nameOfTeacher));

        if(!postgresTeacherRepository.existsByTeacherNickname(nameOfTeacher))
            throw new TeacherNotFoundException("Teacher: {" + nameOfTeacher + "} not found in db");

        if(mongoTeacherRepository.existsByIdAndTaskCatalogName(nameOfTeacher, catalogPayload.name()))
            throw new TaskCatalogWithNameAlreadyExistsException("Task catalog: {" + catalogPayload.name() + "} already exists");

        TeacherMongo teacherMongo = mongoTeacherRepository.findById(nameOfTeacher).get();

        List<TaskMongo> taskMongos = catalogPayload.tasks().stream()
                .map(tp -> TaskMongo.builder()
                        .description(tp.description())
                        .answer(tp.answer())
                        .build())
                .collect(Collectors.toList());

        TaskCatalogMongo newCatalog = TaskCatalogMongo.builder()
                .name(catalogPayload.name())
                .tasks(taskMongos)
                .build();

        teacherMongo.getTaskCatalogs().add(newCatalog);

        mongoTeacherRepository.save(teacherMongo);
    }

    @Override
    public boolean existsTeacher(String nickname) {
        return postgresTeacherRepository.existsByTeacherNickname(nickname);

    }

    @Override
    @Transactional(
            rollbackFor = {
                    Exception.class,
                    Error.class
            }
    )
    public void createTeacher(TeacherPayload teacherPayload) {
        if(postgresTeacherRepository.existsByTeacherNickname(teacherPayload.nickname()))
            throw new TeacherAlreadyExistsException("Teacher: {" + teacherPayload.nickname() + "} already exists");


        TeacherMongo teacherMongo = TeacherMongo.builder()
                            .nicknameOfTeacher(teacherPayload.nickname())
                            .taskCatalogs(new ArrayList<>())
                            .build();


        mongoTeacherRepository.save(teacherMongo);

        TeacherPostgres teacherPostgres = TeacherPostgres.builder()
                .teacherNickname(teacherPayload.nickname())
                .teacherFullname("none")
                .build();

        postgresTeacherRepository.save(teacherPostgres);
    }

    @Override
    public String[] getNamesOfTaskCatalogs(String nameOfTeacher) {
        if (!mongoTeacherRepository.existsById(nameOfTeacher))
            throw new TeacherNotFoundException("Teacher: {" + nameOfTeacher + "} not found in db");

        return mongoTeacherRepository.findTaskCatalogNamesByTeacherId(nameOfTeacher).toArray(new String[0]);

    }
}
