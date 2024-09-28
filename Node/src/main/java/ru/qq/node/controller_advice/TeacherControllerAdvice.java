package ru.qq.node.controller_advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.qq.node.exception.IncorrectExcelFileException;
import ru.qq.node.exception.NotFoundFromDbException;
import ru.qq.node.exception.ConflictFromDbException;

@ControllerAdvice
public class TeacherControllerAdvice {

    @ExceptionHandler(NotFoundFromDbException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundFromDbException ex,
                                                     WebRequest request) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictFromDbException.class)
    public ResponseEntity<?> handleConflictException(ConflictFromDbException ex,
                                                     WebRequest request) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }


    @ExceptionHandler(IncorrectExcelFileException.class)
    public ResponseEntity<?> handleIncorrectExcelFileException(IncorrectExcelFileException ex,
                                                               WebRequest request) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

}
