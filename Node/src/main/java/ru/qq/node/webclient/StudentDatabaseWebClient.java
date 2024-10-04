package ru.qq.node.webclient;

import ru.qq.common.payload.StudentPayload;

public interface StudentDatabaseWebClient {
    boolean existsStudent(String nickname);

    boolean createStudent(StudentPayload studentPayload);

}
