package ru.qq.database.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.qq.common.payload.StudentPayload;
import ru.qq.database.serivce.StudentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("db/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody StudentPayload studentPayload){
        studentService.createStudent(studentPayload);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Success create teacher: {" + studentPayload.nickname() + "}");
    }
}
