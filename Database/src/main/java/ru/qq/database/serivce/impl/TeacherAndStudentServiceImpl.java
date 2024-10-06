package ru.qq.database.serivce.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.qq.database.exception.TeacherAndStudentAlreadyBindException;
import ru.qq.database.exception.student.StudentNotFoundException;
import ru.qq.database.exception.teacher.TeacherNotFoundException;
import ru.qq.database.mongo_db.repository.MongoStudentRepository;
import ru.qq.database.mongo_db.repository.MongoTeacherRepository;
import ru.qq.database.postgres_db.entity.StudentPostgres;
import ru.qq.database.postgres_db.entity.TeacherPostgres;
import ru.qq.database.postgres_db.repository.PostgresStudentRepository;
import ru.qq.database.postgres_db.repository.PostgresTeacherRepository;
import ru.qq.database.serivce.TeacherAndStudentService;

@Service
@RequiredArgsConstructor
@Log4j
public class TeacherAndStudentServiceImpl implements TeacherAndStudentService {

    private final PostgresStudentRepository postgresStudentRepository;
    private final PostgresTeacherRepository postgresTeacherRepository;
    private final MongoStudentRepository mongoStudentRepository;
    private final MongoTeacherRepository mongoTeacherRepository;


    @Override
    @Transactional(
            rollbackFor = {
                    Exception.class,
                    Error.class
            }
    )
    public void bindTeacherAndStudent(String teacherNickname, String studentNickname) {
        if(!postgresTeacherRepository.existsByTeacherNickname(teacherNickname))
            throw new TeacherNotFoundException("Teacher: {" + teacherNickname + "} not found in db");

        if(!postgresStudentRepository.existsByStudentNickname(studentNickname))
            throw new StudentNotFoundException("Student: {" + studentNickname + "} not found in db");

        TeacherPostgres teacherPostgres = postgresTeacherRepository.findByTeacherNickname(teacherNickname).orElseThrow();
        StudentPostgres studentPostgres = postgresStudentRepository.findByStudentNickname(studentNickname).orElseThrow();

        if(teacherPostgres.getStudents().contains(studentPostgres))
            throw new TeacherAndStudentAlreadyBindException("{" + teacherNickname + ", " + studentNickname + "} already bind");


        teacherPostgres.getStudents().add(studentPostgres);
        studentPostgres.getTeachers().add(teacherPostgres);

        postgresTeacherRepository.save(teacherPostgres);
        postgresStudentRepository.save(studentPostgres);


        var mongoStudent = mongoStudentRepository.findById(studentNickname).orElseThrow();
        mongoStudent.getTeacherIds().add(teacherNickname);

        var mongoTeacher = mongoTeacherRepository.findById(teacherNickname).orElseThrow();
        mongoTeacher.getStudentIds().add(studentNickname);

        mongoStudentRepository.save(mongoStudent);
        mongoTeacherRepository.save(mongoTeacher);
    }
}
