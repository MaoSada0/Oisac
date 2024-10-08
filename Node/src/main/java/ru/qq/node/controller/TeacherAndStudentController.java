package ru.qq.node.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qq.node.service.TeacherAndStudentService;

@RestController
@RequestMapping("api/v1/teacher-student")
@RequiredArgsConstructor
public class TeacherAndStudentController {

    private final TeacherAndStudentService teacherAndStudentService;

    @PostMapping("bind")
    public ResponseEntity<?> bindStudentAndTeacher(@RequestParam("teacherNickname") String teacherNickname,
                                                   @RequestParam("studentNickname") String studentNickname){

        boolean isBind = teacherAndStudentService.bindTeacherAndStudent(teacherNickname, studentNickname);

        if(!isBind){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Failed to bind " + teacherNickname + " with " + studentNickname);
        }

        return new ResponseEntity<>("Bind created successfully", HttpStatus.CREATED);
    }
}
