package ru.qq.node.controller.Student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qq.common.payload.StudentPayload;
import ru.qq.node.service.StudentService;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody StudentPayload studentPayload){

        boolean isCreated = studentService.createStudent(studentPayload);

        if (!isCreated) {
            return new ResponseEntity<>("Failed to create teacher", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Student created successfully", HttpStatus.CREATED);

    }


}
