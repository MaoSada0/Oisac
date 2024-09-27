package ru.qq.node.service;

import org.springframework.web.multipart.MultipartFile;
import ru.qq.common.payload.GetTeacherPayload;

public interface MainService {
    boolean processExcel(MultipartFile excelFile, String name, String idOfTeacher);

    boolean createTeacher(GetTeacherPayload teacherPayload);

    boolean existsTeacher(String username);
}
