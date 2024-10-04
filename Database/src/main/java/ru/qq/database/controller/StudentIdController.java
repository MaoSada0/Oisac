package ru.qq.database.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qq.database.serivce.StudentService;

@RestController
@RequestMapping("db/api/v1/student/{nickname}")
@RequiredArgsConstructor
public class StudentIdController {

    private final StudentService studentService;

    @ModelAttribute("nickname")
    public String getStudentNickname(@PathVariable("nickname") String studentNickname) {
        return studentNickname;
    }

    @GetMapping
    public ResponseEntity<?> exitsStudent(@ModelAttribute("nickname") String nickname){

        boolean isExists = studentService.existsStudent(nickname);

        if(isExists)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Found: {" + nickname + "}");

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Not found: {" + nickname + "}");
    }
}
