package ru.qq.node.service;

import org.springframework.web.multipart.MultipartFile;

public interface MainService {
    boolean processExcel(MultipartFile excelFile, String name, String idOfTeacher);
}
