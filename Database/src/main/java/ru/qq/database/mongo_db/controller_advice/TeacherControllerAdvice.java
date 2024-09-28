package ru.qq.database.mongo_db.controller_advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.qq.database.mongo_db.exception.TaskCatalogWithNameAlreadyExistsException;
import ru.qq.database.mongo_db.exception.TeacherAlreadyExistsException;
import ru.qq.database.mongo_db.exception.TeacherNotFoundException;

@ControllerAdvice
public class TeacherControllerAdvice {

    @ExceptionHandler(TeacherNotFoundException.class)
    public ResponseEntity<?> handleTeacherNotFoundException(TeacherNotFoundException ex,
                                                                 WebRequest request) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TeacherAlreadyExistsException.class)
    public ResponseEntity<?> handleTeacherAlreadyExistsException(TeacherAlreadyExistsException ex,
                                                                 WebRequest request) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TaskCatalogWithNameAlreadyExistsException.class)
    public ResponseEntity<?> handleTaskCatalogWithNameAlreadyExistsException(TaskCatalogWithNameAlreadyExistsException ex,
                                                                 WebRequest request) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
