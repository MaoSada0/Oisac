package ru.qq.node.service;

import org.springframework.web.multipart.MultipartFile;
import ru.qq.common.payload.TeacherPayload;

public interface TeacherService {
    boolean processExcel(MultipartFile excelFile, String name, String idOfTeacher);

    boolean createTeacher(TeacherPayload teacherPayload);

    boolean existsTeacher(String username);

    String[] getNamesOfTasks(String id);
}
