package ru.qq.node.controller.Teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.qq.node.service.MainService;

@RestController
@RequestMapping("api/v1/teacher/{teacherName}/task")
@RequiredArgsConstructor
public class TaskOfTeacherController {

    private final MainService mainService;

    @GetMapping("names")
    public ResponseEntity<String[]> getTasks(@PathVariable("teacherName") String id){
        String[] returnAns = mainService.getNamesOfTasks(id);

        return new ResponseEntity<>(returnAns, HttpStatus.OK);
    }
}
