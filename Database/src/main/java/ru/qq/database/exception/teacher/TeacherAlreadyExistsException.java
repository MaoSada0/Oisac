package ru.qq.database.exception.teacher;

public class TeacherAlreadyExistsException extends RuntimeException {
    public TeacherAlreadyExistsException(String message) {
        super(message);
    }
}
