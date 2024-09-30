package ru.qq.node.controller.Teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qq.common.payload.TeacherPayload;
import ru.qq.node.service.MainService;

@RestController
@RequestMapping("api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final MainService mainService;


    @PostMapping
    public ResponseEntity<?> createTeacher(@RequestBody TeacherPayload teacherPayload){

        boolean isCreated = mainService.createTeacher(teacherPayload);

        if (isCreated) {
            return new ResponseEntity<>("Teacher created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create teacher", HttpStatus.BAD_REQUEST);
        }
    }

}
