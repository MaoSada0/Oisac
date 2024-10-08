package ru.qq.database.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qq.database.serivce.TeacherAndStudentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("db/api/v1/teachers-students")
public class TeacherAndStudentController {

    private final TeacherAndStudentService teacherAndStudentService;

    @PatchMapping("bind")
    public ResponseEntity<?> bindTeacherAndStudent(@RequestParam("teacherNickname") String teacherNickname,
                                                   @RequestParam("studentNickname") String studentNickname){
        teacherAndStudentService.bindTeacherAndStudent(teacherNickname, studentNickname);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Success bind {" + teacherNickname + ", " + studentNickname + "}");
    }
}
