package ru.qq.node.webclient;

import ru.qq.common.payload.TeacherPayload;
import ru.qq.common.payload.TaskCatalogPayload;




public interface DatabaseWebClient {
    boolean saveTasks(TaskCatalogPayload taskCatalogPayload, String nameOfTeacher);
    boolean createTeacher(TeacherPayload teacherPayload);

    boolean existsTeacher(String nameOfTeacher);

    String[] getNamesOfTasks(String nameOfTeacher);
}
