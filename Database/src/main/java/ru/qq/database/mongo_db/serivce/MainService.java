package ru.qq.database.mongo_db.serivce;

import ru.qq.common.payload.GetTeacherPayload;
import ru.qq.common.payload.TaskCatalogPayload;

public interface MainService {
    boolean saveTaskCatalog(String nameOfTeacher, TaskCatalogPayload catalogPayload);

    boolean existsTeacher(String id);

    boolean createTeacher(GetTeacherPayload teacherPayload);

}
