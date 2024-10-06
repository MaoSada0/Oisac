package ru.qq.node.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import ru.qq.common.payload.AddTaskCatalogToStudentPayload;
import ru.qq.node.service.StudentOfTeacherService;
import ru.qq.node.webclient.StudentOfTeacherWebClient;

@Service
@RequiredArgsConstructor
@Log4j
public class StudentOfTeacherServiceImpl implements StudentOfTeacherService {

    private final StudentOfTeacherWebClient studentOfTeacherWebClient;

    @Override
    public boolean addTasksToStudent(String teacherNickname, String studentNickname, AddTaskCatalogToStudentPayload payload) {
        return studentOfTeacherWebClient.addTasksToStudent(teacherNickname, studentNickname, payload);
    }
}
