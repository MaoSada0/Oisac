package ru.qq.node.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.qq.node.service.TeacherStudentService;
import ru.qq.node.webclient.TeacherStudentWebclient;

@Service
@RequiredArgsConstructor
public class TeacherStudentServiceImpl implements TeacherStudentService {

    private final TeacherStudentWebclient teacherStudentWebclient;

    @Override
    public boolean bindTeacherAndStudent(String teacherNickname, String studentNickname) {
        return teacherStudentWebclient.bindTeacherAndStudent(teacherNickname, studentNickname);
    }
}
