package ru.qq.database.controller_advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.qq.database.exception.teacher.NoSuchTaskCatalogException;
import ru.qq.database.exception.teacher.TaskCatalogWithNameAlreadyExistsException;
import ru.qq.database.exception.teacher.TeacherAlreadyExistsException;
import ru.qq.database.exception.teacher.TeacherNotFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class TeacherControllerAdvice {

    @ExceptionHandler(TeacherNotFoundException.class)
    public ResponseEntity<?> handleTeacherNotFoundException(TeacherNotFoundException ex,
                                                                 WebRequest request) {

        Map<String, Object> errorDetails = getErrorDetails(HttpStatus.NOT_FOUND.value(),
                "TeacherNotFoundException",
                ex.getMessage(),
                request.getContextPath());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TeacherAlreadyExistsException.class)
    public ResponseEntity<?> handleTeacherAlreadyExistsException(TeacherAlreadyExistsException ex,
                                                                 WebRequest request) {

        Map<String, Object> errorDetails = getErrorDetails(HttpStatus.CONFLICT.value(),
                "TeacherAlreadyExistsException",
                ex.getMessage(),
                request.getContextPath());

        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TaskCatalogWithNameAlreadyExistsException.class)
    public ResponseEntity<?> handleTaskCatalogWithNameAlreadyExistsException(TaskCatalogWithNameAlreadyExistsException ex,
                                                                 WebRequest request) {

        Map<String, Object> errorDetails = getErrorDetails(HttpStatus.CONFLICT.value(),
                "TaskCatalogWithNameAlreadyExistsException",
                ex.getMessage(),
                request.getContextPath());

        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoSuchTaskCatalogException.class)
    public ResponseEntity<?> handleNoSuchTaskCatalogException(NoSuchTaskCatalogException ex,
                                                                             WebRequest request) {

        Map<String, Object> errorDetails = getErrorDetails(HttpStatus.NOT_FOUND.value(),
                "NoSuchTaskCatalogException",
                ex.getMessage(),
                request.getContextPath());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
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
