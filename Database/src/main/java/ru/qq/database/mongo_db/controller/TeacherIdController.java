package ru.qq.database.mongo_db.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qq.common.payload.TaskCatalogPayload;
import ru.qq.database.mongo_db.serivce.MainService;

@RestController
@RequiredArgsConstructor
@RequestMapping("db/mongo/api/v1/{id}")
public class TeacherIdController {

    private final MainService mainService;

    @PostMapping("uploadTasks")
    public ResponseEntity<?> uploadTasks(@PathVariable("id") String id,
                                        @RequestBody TaskCatalogPayload catalogPayload){
        boolean isOk = mainService.saveTaskCatalog(id, catalogPayload);

        return new ResponseEntity<>(isOk, HttpStatus.OK);
    }

}
