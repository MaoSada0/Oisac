package ru.qq.node.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.qq.common.payload.TeacherPayload;
import ru.qq.common.payload.TaskCatalogPayload;
import ru.qq.common.payload.TaskPayload;
import ru.qq.node.exception.IncorrectExcelFileException;
import ru.qq.node.service.TeacherService;
import ru.qq.node.webclient.DatabaseWebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final DatabaseWebClient databaseWebClient;

    @Override
    public boolean processExcel(MultipartFile excelFile, String name, String idOfTeacher) {

        List<TaskPayload> tasksAndAnswers = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(excelFile.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if(row.getPhysicalNumberOfCells() > 2){
                    throw new IncorrectExcelFileException("Incorrect excel file, must be only 2 columns");
                }
                TaskPayload taskPayload = new TaskPayload(row.getCell(0).toString(),
                        row.getCell(1).toString());
                tasksAndAnswers.add(taskPayload);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        TaskCatalogPayload taskCatalogPayload = new TaskCatalogPayload(name, tasksAndAnswers);

        return databaseWebClient.saveTasks(taskCatalogPayload, idOfTeacher);
    }

    @Override
    public boolean createTeacher(TeacherPayload teacherPayload) {
        return databaseWebClient.createTeacher(teacherPayload);
    }

    @Override
    public boolean existsTeacher(String username) {
        return databaseWebClient.existsTeacher(username);
    }

    @Override
    public String[] getNamesOfTasks(String id) {
        return databaseWebClient.getNamesOfTasks(id);
    }
}
