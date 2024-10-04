package ru.qq.database.exception;

public class TeacherAndStudentAlreadyBindException extends RuntimeException {
    public TeacherAndStudentAlreadyBindException(String message) {
        super(message);
    }
}
