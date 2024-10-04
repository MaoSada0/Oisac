package ru.qq.database.serivce.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.qq.common.payload.StudentPayload;
import ru.qq.database.exception.student.StudentAlreadyExistsException;
import ru.qq.database.mongo_db.document.StudentMongo;
import ru.qq.database.mongo_db.repository.MongoStudentRepository;
import ru.qq.database.postgres_db.entity.StudentPostgres;
import ru.qq.database.postgres_db.repository.PostgresStudentRepository;
import ru.qq.database.serivce.StudentService;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Log4j
public class StudentServiceImpl implements StudentService {

    private final MongoStudentRepository mongoStudentRepository;
    private final PostgresStudentRepository postgresStudentRepository;

    @Override
    @Transactional(
            rollbackFor = {
                    Exception.class,
                    Error.class
            }
    )
    public void createStudent(StudentPayload studentPayload) {
        log.debug(postgresStudentRepository.existsByStudentNickname(studentPayload.nickname()));
        if(mongoStudentRepository.existsById(studentPayload.nickname()))
            throw new StudentAlreadyExistsException("Student: {" + studentPayload.nickname() + "} already exists");

        StudentMongo studentMongo = StudentMongo.builder()
                .nicknameOfStudent(studentPayload.nickname())
                .taskCatalogs(new ArrayList<>())
                .build();

        mongoStudentRepository.save(studentMongo);

        StudentPostgres studentPostgres = StudentPostgres.builder()
                .studentNickname(studentPayload.nickname())
                .studentFullname("none")
                .build();

        postgresStudentRepository.save(studentPostgres);
    }

    @Override
    public boolean existsStudent(String nickname) {
        return postgresStudentRepository.existsByStudentNickname(nickname);
    }
}
