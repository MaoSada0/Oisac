package ru.qq.database.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qq.common.payload.TeacherPayload;
import ru.qq.database.serivce.MainService;

@RestController
@RequestMapping("db/mongo/api/v1/")
@RequiredArgsConstructor
public class TeachersController {

    private final MainService mainService;

    @PostMapping("createTeacher")
    public ResponseEntity<?> createTeacher(@RequestBody TeacherPayload teacherPayload){
        mainService.createTeacher(teacherPayload);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Success create teacher: {" + teacherPayload + "}");
    }

    @GetMapping("exists")
    public ResponseEntity<?> existsTeacher(@RequestParam("id") String id){
        boolean isOk = mainService.existsTeacher(id);

        if(isOk)
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body("Found: {" + id + "}");

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Not found: {" + id + "}");
    }
}
