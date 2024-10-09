package ru.qq.node.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import ru.qq.common.payload.AddTaskCatalogToStudentPayload;
import ru.qq.common.payload.NotificationPayload;
import ru.qq.node.service.KafkaService;
import ru.qq.node.service.StudentOfTeacherService;
import ru.qq.node.webclient.StudentOfTeacherWebClient;

@Service
@RequiredArgsConstructor
@Log4j
public class StudentOfTeacherServiceImpl implements StudentOfTeacherService {

    private final StudentOfTeacherWebClient studentOfTeacherWebClient;
    private final KafkaService kafkaService;

    @Override
    public boolean addTasksToStudent(String teacherNickname, String studentNickname, AddTaskCatalogToStudentPayload payload) {
        boolean isSuccess = studentOfTeacherWebClient.addTasksToStudent(teacherNickname, studentNickname, payload);

        if(isSuccess){
            kafkaService.processNotification(
                    new NotificationPayload("Notification: " + teacherNickname + " and " + studentNickname)
            );
        }

        return isSuccess;
    }
}
