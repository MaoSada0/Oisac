package ru.qq.database.controller.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qq.common.payload.AddTaskCatalogToStudentPayload;
import ru.qq.database.serivce.StudentOfTeacherService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("db/api/v1/teacher/{nicknameOfTeacher}/students/{nicknameOfStudent}")
@RequiredArgsConstructor
@Log4j
public class TeacherIdStudentIdController {

    private final StudentOfTeacherService studentOfTeacherService;

    @ModelAttribute("nicknames")
    public Map<String, String> getNicknames(@PathVariable("nicknameOfTeacher") String nicknameOfTeacher,
                                            @PathVariable("nicknameOfStudent") String nicknameOfStudent) {

        Map<String, String> nicknames = new HashMap<>();
        nicknames.put("teacher", nicknameOfTeacher);
        nicknames.put("student", nicknameOfStudent);
        return nicknames;
    }

    @PostMapping("tasks/add")
    public ResponseEntity<?> addTaskCatalogToStudent(@ModelAttribute("nicknames") Map<String, String> nicknames,
                                                     @RequestBody AddTaskCatalogToStudentPayload payload){

        studentOfTeacherService.addTasksToStudent(
                nicknames.get("teacher"),
                nicknames.get("student"),
                payload);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Success add: {" +  nicknames.get("teacher") + ", " +  nicknames.get("student")+ "}");
    }
}
