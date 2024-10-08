package ru.qq.node.controller.Teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.qq.node.service.TeacherService;


@RestController
@RequestMapping("api/v1/teacher/{nickname}")
@RequiredArgsConstructor
@Log4j
public class TeacherIdController {

    private final TeacherService teacherService;

    @ModelAttribute("nickname")
    public String getTeacherNickname(@PathVariable("nickname") String teacherNickname) {
        return teacherNickname;
    }

    @PostMapping("tasks/upload")
    public ResponseEntity<?> uploadTask(@ModelAttribute("nickname") String nickname,
                                        @RequestParam("file") MultipartFile file,
                                        @RequestParam("name") String name){
        boolean isOk = teacherService.processFile(file, name, nickname);

        if (!isOk) {
            return new ResponseEntity<>("Failed to upload task", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Task uploaded successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> existsTeacher(@ModelAttribute("nickname") String nickname){
        boolean exists = teacherService.existsTeacher(nickname);

        if (!exists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found: " + nickname);
        }

        return ResponseEntity.ok("Teacher found: " + nickname);
    }


}
