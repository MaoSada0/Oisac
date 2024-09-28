package ru.qq.database.mongo_db.serivce;

import org.springframework.http.ResponseEntity;
import ru.qq.common.payload.TeacherPayload;
import ru.qq.common.payload.TaskCatalogPayload;

public interface MainService {
    void saveTaskCatalog(String nameOfTeacher, TaskCatalogPayload catalogPayload);

    boolean existsTeacher(String nameOfTeacher);

    void createTeacher(TeacherPayload teacherPayload);

    String[] getNamesOfTaskCatalogs(String nameOfTeacher);
}
