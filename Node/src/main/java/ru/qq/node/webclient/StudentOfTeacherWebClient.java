package ru.qq.node.webclient;

import ru.qq.common.payload.AddTaskCatalogToStudentPayload;

public interface StudentOfTeacherWebClient {

    boolean addTasksToStudent(String teacherNickname, String studentNickname, AddTaskCatalogToStudentPayload payload);

}
