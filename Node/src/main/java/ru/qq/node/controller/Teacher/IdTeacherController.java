package ru.qq.node.controller.Teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.qq.node.service.TeacherService;


@RestController
@RequestMapping("api/v1/teacher/{nickname}")
@RequiredArgsConstructor
public class IdTeacherController {

    private final TeacherService teacherService;

    @ModelAttribute("teacherNickname")
    public String getTeacherNickname(@PathVariable("nickname") String teacherNickname) {
        return teacherNickname;
    }

    @PostMapping("upload")
    public ResponseEntity<?> uploadTask(@ModelAttribute("teacherNickname") String username,
                                        @RequestParam("file") MultipartFile file,
                                        @RequestParam("name") String name){
        boolean isOk = teacherService.processExcel(file, name, username);

        if (isOk) {
            return new ResponseEntity<>("Task uploaded successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to upload task", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> existsTeacher(@ModelAttribute("teacherNickname") String username){
        boolean exists = teacherService.existsTeacher(username);

        if (exists) {
            return ResponseEntity.ok("Teacher found: " + username);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found: " + username);
        }
    }


}
