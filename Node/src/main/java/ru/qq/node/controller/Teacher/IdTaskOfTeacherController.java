package ru.qq.node.controller.Teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/teacher/{teacherName}/task/{taskName}")
@RequiredArgsConstructor
public class IdTaskOfTeacherController {
}
