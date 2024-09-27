package ru.qq.database.mongo_db.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qq.common.payload.GetTeacherPayload;
import ru.qq.database.mongo_db.serivce.MainService;

@RestController
@RequestMapping("db/mongo/api/v1/")
@RequiredArgsConstructor
public class TeachersController {

    private final MainService mainService;

    @PostMapping("createTeacher")
    public ResponseEntity<?> createTeacher(@RequestBody GetTeacherPayload teacherPayload){
        boolean isOk = mainService.createTeacher(teacherPayload);

        return new ResponseEntity<>(isOk, HttpStatus.OK);
    }

    @GetMapping("exists")
    public ResponseEntity<?> existsTeacher(@RequestParam("id") String id){
        boolean isOk = mainService.existsTeacher(id);

        return new ResponseEntity<>(isOk, HttpStatus.OK);
    }
}
