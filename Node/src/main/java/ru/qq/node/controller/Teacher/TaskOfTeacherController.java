package ru.qq.node.controller.Teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.qq.node.service.TeacherService;

@RestController
@RequestMapping("api/v1/teacher/{teacherName}/tasks")
@RequiredArgsConstructor
public class TaskOfTeacherController {

    private final TeacherService teacherService;

    @GetMapping("names")
    public ResponseEntity<String[]> getTasks(@PathVariable("teacherName") String teacherName){
        String[] returnAns = teacherService.getNamesOfTasks(teacherName);

        return new ResponseEntity<>(returnAns, HttpStatus.OK);
    }
}
