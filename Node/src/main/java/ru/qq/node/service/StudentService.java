package ru.qq.node.service;

import ru.qq.common.payload.StudentPayload;

public interface StudentService {
    boolean existsStudent(String nickname);

    boolean createStudent(StudentPayload studentPayload);
}
