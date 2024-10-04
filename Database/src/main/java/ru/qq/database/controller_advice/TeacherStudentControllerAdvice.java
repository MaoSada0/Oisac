package ru.qq.database.controller_advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.qq.database.exception.TeacherAndStudentAlreadyBindException;

@ControllerAdvice
public class TeacherStudentControllerAdvice {

    @ExceptionHandler(TeacherAndStudentAlreadyBindException.class)
    public ResponseEntity<?> handleTeacherAndStudentAlreadyBindException(TeacherAndStudentAlreadyBindException ex,
                                                            WebRequest request) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
