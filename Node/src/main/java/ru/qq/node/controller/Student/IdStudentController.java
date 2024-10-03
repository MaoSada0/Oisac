package ru.qq.node.controller.Student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qq.node.service.StudentService;

@RestController
@RequestMapping("api/v1/student/{nickname}")
@RequiredArgsConstructor
public class IdStudentController {

    private final StudentService studentService;


    @ModelAttribute("studentNickname")
    public String getStudentNickname(@PathVariable("nickname") String studentNickname) {
        return studentNickname;
    }

    @GetMapping
    public ResponseEntity<?> existsStudent(@ModelAttribute("studentNickname") String nickname){
        boolean exists = studentService.existsStudent(nickname);

        if (exists) {
            return ResponseEntity.ok("Student found: " + nickname);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found: " + nickname);
        }
    }

}
