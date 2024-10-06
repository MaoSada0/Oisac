package ru.qq.node.controller_advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.qq.node.exception.IncorrectExcelFileException;
import ru.qq.node.exception.IncorrectTypeFileException;
import ru.qq.node.exception.NotFoundFromDbException;
import ru.qq.node.exception.ConflictFromDbException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class EntityControllerAdvice {

    @ExceptionHandler(NotFoundFromDbException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundFromDbException ex,
                                                     WebRequest request) {

        Map<String, Object> errorDetails = getErrorDetails(HttpStatus.NOT_FOUND.value(),
                "NotFoundFromDbException",
                ex.getMessage(),
                request.getContextPath());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictFromDbException.class)
    public ResponseEntity<?> handleConflictException(ConflictFromDbException ex,
                                                     WebRequest request) {

        Map<String, Object> errorDetails = getErrorDetails(HttpStatus.CONFLICT.value(),
                "ConflictFromDbException",
                ex.getMessage(),
                request.getContextPath());

        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(IncorrectExcelFileException.class)
    public ResponseEntity<?> handleIncorrectExcelFileException(IncorrectExcelFileException ex,
                                                               WebRequest request) {

        Map<String, Object> errorDetails = getErrorDetails(HttpStatus.NOT_ACCEPTABLE.value(),
                "IncorrectExcelFileException",
                ex.getMessage(),
                request.getContextPath());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(IncorrectTypeFileException.class)
    public ResponseEntity<?> handleIncorrectTypeFileException(IncorrectTypeFileException ex,
                                                               WebRequest request) {

        Map<String, Object> errorDetails = getErrorDetails(HttpStatus.NOT_ACCEPTABLE.value(),
                "IncorrectExcelFileException",
                ex.getMessage(),
                request.getContextPath());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
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
