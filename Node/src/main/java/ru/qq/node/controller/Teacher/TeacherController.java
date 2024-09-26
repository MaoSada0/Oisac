package ru.qq.node.controller.Teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.qq.node.service.MainService;

@RestController
@RequestMapping("api/v1/teacher/{id}")
@RequiredArgsConstructor
public class TeacherController {

    private final MainService mainService;

    @PostMapping("uploadExcelTasks")
    public ResponseEntity<?> uploadTask(@PathVariable("id") String id,
                                        @RequestParam("file") MultipartFile file,
                                        @RequestParam("name") String name){
        boolean isOk = mainService.processExcel(file, name, id);

        if(isOk) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
