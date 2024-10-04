package ru.qq.database.serivce;

import ru.qq.common.payload.StudentPayload;

public interface StudentService {
    void createStudent(StudentPayload studentPayload);

    boolean existsStudent(String nickname);
}
