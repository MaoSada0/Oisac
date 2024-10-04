package ru.qq.database.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qq.common.payload.TaskCatalogPayload;
import ru.qq.database.serivce.TeacherService;

@RestController
@RequiredArgsConstructor
@RequestMapping("db/api/v1/teacher/{nickname}")
@Log4j
public class TeacherIdController {

    private final TeacherService teacherService;

    @ModelAttribute("nickname")
    public String getTeacherNickname(@PathVariable("nickname") String teacherNickname) {
        return teacherNickname;
    }

    @PostMapping("upload")
    public ResponseEntity<?> uploadTasks(@ModelAttribute("nickname") String nickname,
                                        @RequestBody TaskCatalogPayload catalogPayload){
        teacherService.saveTaskCatalog(nickname, catalogPayload);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Success save: {" + nickname + ", " + catalogPayload.name() + "}");
    }

    @GetMapping("task/names")
    public ResponseEntity<String[]> getAllTaskCatalogNames(@ModelAttribute("nickname") String nickname){
        String[] returnAns = teacherService.getNamesOfTaskCatalogs(nickname);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(returnAns);
    }


    @GetMapping
    public ResponseEntity<?> existsTeacher(@ModelAttribute("nickname") String nickname) {
        boolean isExists = teacherService.existsTeacher(nickname);
        log.debug(isExists);

        if(isExists)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Found: {" + nickname + "}");

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Not found: {" + nickname + "}");
    }
}
