package ru.qq.node.controller.Teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.qq.node.service.MainService;


@RestController
@RequestMapping("api/v1/teacher/{teacherName}")
@RequiredArgsConstructor
public class IdTeacherController {

    private final MainService mainService;

    @PostMapping("upload")
    public ResponseEntity<?> uploadTask(@PathVariable("teacherName") String username,
                                        @RequestParam("file") MultipartFile file,
                                        @RequestParam("name") String name){
        boolean isOk = mainService.processExcel(file, name, username);

        if (isOk) {
            return new ResponseEntity<>("Task uploaded successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to upload task", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> existsTeacher(@PathVariable("teacherName") String username){
        boolean exists = mainService.existsTeacher(username);

        if (exists) {
            return ResponseEntity.ok("Teacher found: " + username);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found: " + username);
        }
    }
}
