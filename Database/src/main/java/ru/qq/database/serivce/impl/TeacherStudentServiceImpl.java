package ru.qq.database.serivce.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.qq.database.exception.TeacherAndStudentAlreadyBindException;
import ru.qq.database.exception.student.StudentNotFoundException;
import ru.qq.database.exception.teacher.TeacherNotFoundException;
import ru.qq.database.postgres_db.entity.StudentPostgres;
import ru.qq.database.postgres_db.entity.TeacherPostgres;
import ru.qq.database.postgres_db.repository.PostgresStudentRepository;
import ru.qq.database.postgres_db.repository.PostgresTeacherRepository;
import ru.qq.database.serivce.TeacherStudentService;

@Service
@RequiredArgsConstructor
public class TeacherStudentServiceImpl implements TeacherStudentService {

    private final PostgresStudentRepository postgresStudentRepository;
    private final PostgresTeacherRepository postgresTeacherRepository;

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

        TeacherPostgres teacherPostgres = postgresTeacherRepository.findByTeacherNickname(teacherNickname).get();
        StudentPostgres studentPostgres = postgresStudentRepository.findByStudentNickname(studentNickname).get();

        if(teacherPostgres.getStudents().contains(studentPostgres))
            throw new TeacherAndStudentAlreadyBindException("{" + teacherNickname + ", " + studentNickname + "} already bind");


        teacherPostgres.getStudents().add(studentPostgres);
        studentPostgres.getTeachers().add(teacherPostgres);
    }
}
