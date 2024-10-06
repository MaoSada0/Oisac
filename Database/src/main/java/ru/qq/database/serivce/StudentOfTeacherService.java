package ru.qq.database.serivce;

import ru.qq.common.payload.AddTaskCatalogToStudentPayload;

public interface StudentOfTeacherService {

    boolean addTasksToStudent(String teacher, String student, AddTaskCatalogToStudentPayload payload);
}
