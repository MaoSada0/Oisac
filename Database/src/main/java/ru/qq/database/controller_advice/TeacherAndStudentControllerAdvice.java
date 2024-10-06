package ru.qq.database.controller_advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.qq.database.exception.TeacherAndStudentAlreadyBindException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class TeacherAndStudentControllerAdvice {

    @ExceptionHandler(TeacherAndStudentAlreadyBindException.class)
    public ResponseEntity<?> handleTeacherAndStudentAlreadyBindException(TeacherAndStudentAlreadyBindException ex,
                                                            WebRequest request) {

        Map<String, Object> errorDetails = getErrorDetails(HttpStatus.CONFLICT.value(),
                "TeacherAndStudentAlreadyBindException",
                ex.getMessage(),
                request.getContextPath());

        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    private Map<String, Object> getErrorDetails(int status, String error, String message, String path){
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("status", status);
        errorDetails.put("error", error);
        errorDetails.put("message", message);
        errorDetails.put("path", path);

        return errorDetails;
    }
}
