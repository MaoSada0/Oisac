package ru.qq.node.service.impl;

import org.springframework.stereotype.Service;
import ru.qq.node.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public boolean existsStudent(String nickname) {
        return false;
    }
}
