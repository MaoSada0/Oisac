package ru.qq.node.service;

import ru.qq.common.payload.AddTaskCatalogToStudentPayload;

public interface StudentOfTeacherService {

    boolean addTasksToStudent(String teacherNickname, String studentNickname, AddTaskCatalogToStudentPayload payload);
}
