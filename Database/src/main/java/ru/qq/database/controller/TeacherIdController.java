package ru.qq.database.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qq.common.payload.TaskCatalogPayload;
import ru.qq.database.serivce.MainService;

@RestController
@RequiredArgsConstructor
@RequestMapping("db/mongo/api/v1/{id}")
public class TeacherIdController {

    private final MainService mainService;

    @PostMapping("uploadTasks")
    public ResponseEntity<?> uploadTasks(@PathVariable("id") String id,
                                        @RequestBody TaskCatalogPayload catalogPayload){
        mainService.saveTaskCatalog(id, catalogPayload);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Success save: {" + id + ", " + catalogPayload.name() + "}");
    }

    @GetMapping("getNamesOfTasks")
    public ResponseEntity<String[]> getAllTaskCatalogNames(@PathVariable("id") String id){
        String[] returnAns = mainService.getNamesOfTaskCatalogs(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(returnAns);
    }

}
