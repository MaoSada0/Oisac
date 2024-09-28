package ru.qq.node.controller.Student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qq.common.payload.StudentPayload;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentsController {

    @GetMapping("exists")
    public ResponseEntity<Boolean> existsStudent(@RequestParam("username") String username){

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<?> createStudent(@RequestBody StudentPayload studentPayload){

        return ResponseEntity.ok().build();
    }


}
