package ru.qq.database.mongo_db.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.qq.common.payload.TaskCatalogPayload;

@RestController
@RequiredArgsConstructor
@RequestMapping("db/api/v1")
public class TeacherController {

    @PostMapping("{id}/uploadTasks")
    public ResponseEntity<?> uploadTask(@PathVariable("id") String id,
                                        @RequestBody TaskCatalogPayload catalogPayload){

        return ResponseEntity.ok().build();
    }
}
