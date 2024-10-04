package ru.qq.database.controller_advice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.qq.database.exception.student.StudentAlreadyExistsException;
import ru.qq.database.exception.student.StudentNotFoundException;


@ControllerAdvice
@RequiredArgsConstructor
public class StudentControllerAdvice {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<?> handleStudentNotFoundException(StudentNotFoundException ex,
                                                            WebRequest request) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<?> handleStudentAlreadyExistsException(StudentAlreadyExistsException ex,
                                                                 WebRequest request) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

}
