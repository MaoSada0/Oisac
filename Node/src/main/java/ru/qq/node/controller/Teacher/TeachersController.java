package ru.qq.node.controller.Teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qq.node.payload.GetTeacherPayload;
import ru.qq.node.service.MainService;

@RestController
@RequestMapping("api/v1/teacher")
@RequiredArgsConstructor
public class TeachersController {

    private final MainService mainService;

    @GetMapping("exists")
    public ResponseEntity<Boolean> existsTeacher(@RequestParam("username") String username){

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<?> createTeacher(@RequestBody GetTeacherPayload teacherPayload){

        return ResponseEntity.ok().build();
    }



}
