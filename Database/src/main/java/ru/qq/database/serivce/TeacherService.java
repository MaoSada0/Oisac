package ru.qq.database.serivce;

import ru.qq.common.payload.TeacherPayload;
import ru.qq.common.payload.TaskCatalogPayload;

public interface TeacherService {
    void saveTaskCatalog(String nameOfTeacher, TaskCatalogPayload catalogPayload);

    boolean existsTeacher(String nickname);

    void createTeacher(TeacherPayload teacherPayload);

    String[] getNamesOfTaskCatalogs(String nameOfTeacher);
}
