package ru.qq.database.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qq.common.payload.TeacherPayload;
import ru.qq.database.serivce.TeacherService;

@RestController
@RequestMapping("db/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<?> createTeacher(@RequestBody TeacherPayload teacherPayload){
        teacherService.createTeacher(teacherPayload);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Success create teacher: {" + teacherPayload.nickname() + "}");
    }


}
