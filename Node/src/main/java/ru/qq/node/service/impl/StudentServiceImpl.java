package ru.qq.node.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.qq.common.payload.StudentPayload;
import ru.qq.node.service.StudentService;
import ru.qq.node.webclient.StudentDatabaseWebClient;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentDatabaseWebClient studentDatabaseWebClient;

    @Override
    @Cacheable(value = "existsStudent", key = "#nickname", cacheManager = "cacheManager")
    public boolean existsStudent(String nickname) {
        return studentDatabaseWebClient.existsStudent(nickname);
    }

    @Override
    public boolean createStudent(StudentPayload studentPayload) {
        return studentDatabaseWebClient.createStudent(studentPayload);
    }
}
