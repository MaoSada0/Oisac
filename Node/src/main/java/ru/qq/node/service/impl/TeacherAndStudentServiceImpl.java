package ru.qq.node.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.qq.node.service.TeacherAndStudentService;
import ru.qq.node.webclient.TeacherAndStudentWebclient;

@Service
@RequiredArgsConstructor
public class TeacherAndStudentServiceImpl implements TeacherAndStudentService {

    private final TeacherAndStudentWebclient teacherAndStudentWebclient;

    @Override
    public boolean bindTeacherAndStudent(String teacherNickname, String studentNickname) {
        return teacherAndStudentWebclient.bindTeacherAndStudent(teacherNickname, studentNickname);
    }
}
