package ru.qq.database.controller_advice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.qq.database.exception.student.StudentAlreadyExistsException;
import ru.qq.database.exception.student.StudentNotFoundException;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
@RequiredArgsConstructor
public class StudentControllerAdvice {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<?> handleStudentNotFoundException(StudentNotFoundException ex,
                                                            WebRequest request) {

        Map<String, Object> errorDetails = getErrorDetails(HttpStatus.NOT_FOUND.value(),
                "StudentNotFoundException",
                ex.getMessage(),
                request.getContextPath());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<?> handleStudentAlreadyExistsException(StudentAlreadyExistsException ex,
                                                                 WebRequest request) {

        Map<String, Object> errorDetails = getErrorDetails(HttpStatus.CONFLICT.value(),
                "StudentAlreadyExistsException",
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
