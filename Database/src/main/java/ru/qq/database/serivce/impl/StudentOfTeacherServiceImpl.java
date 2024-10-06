package ru.qq.database.serivce.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.qq.common.payload.AddTaskCatalogToStudentPayload;
import ru.qq.database.exception.student.StudentNotFoundException;
import ru.qq.database.exception.teacher.NoSuchTaskCatalogException;
import ru.qq.database.exception.teacher.TeacherNotFoundException;
import ru.qq.database.mongo_db.document.TaskCatalogForTeacherMongo;
import ru.qq.database.mongo_db.document.TaskForTeacherMongo;
import ru.qq.database.mongo_db.document.student.StudentMongo;
import ru.qq.database.mongo_db.document.student.TaskCatalogForStudentMongo;
import ru.qq.database.mongo_db.document.student.TaskForStudentMongo;
import ru.qq.database.mongo_db.repository.MongoStudentRepository;
import ru.qq.database.mongo_db.repository.MongoTeacherRepository;
import ru.qq.database.serivce.StudentOfTeacherService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentOfTeacherServiceImpl implements StudentOfTeacherService {

    private final MongoTeacherRepository mongoTeacherRepository;
    private final MongoStudentRepository mongoStudentRepository;

    @Override
    @Transactional(rollbackFor = {
            Error.class,
            RuntimeException.class,
            Exception.class
    })
    public boolean addTasksToStudent(String teacherNickname, String studentNickname, AddTaskCatalogToStudentPayload payload) {
        if(!mongoStudentRepository.existsById(studentNickname))
            throw new StudentNotFoundException(studentNickname + " not found in db");

        if(!mongoTeacherRepository.existsById(teacherNickname))
            throw new TeacherNotFoundException(teacherNickname + " not found in db");

        if(!mongoTeacherRepository.existsByTeacherIdAndTaskCatalogId(teacherNickname, payload.nameOfTaskCatalog()))
            throw new NoSuchTaskCatalogException(payload.nameOfTaskCatalog() + " not found");

        TaskCatalogForTeacherMongo teacherCatalog = mongoTeacherRepository.
                findTaskCatalogByTeacherIdAndCatalogId(teacherNickname,
                        payload.nameOfTaskCatalog())
                .orElseThrow();

        List<TaskForStudentMongo> tasksForStudent =
                teacherCatalog.getTasks().stream()
                .map(t -> TaskForStudentMongo.builder()
                        .Id(t.getId())
                        .rightAnswer(t.getAnswer())
                        .description(t.getDescription())
                        .build())
                .toList();


        TaskCatalogForStudentMongo studentCatalog = TaskCatalogForStudentMongo.builder()
                .Id(payload.nameOfTaskCatalog())
                .name(payload.nameOfTaskCatalog())
                .tasks(tasksForStudent)
                .isDone(false)
                .isDoneWithMistakes(false)
                .attempts(new ArrayList<>())
                .build();

        StudentMongo student = mongoStudentRepository.findById(studentNickname).orElseThrow();

        student.getTaskCatalogs().add(studentCatalog);

        mongoStudentRepository.save(student);

        return true;
    }
}
