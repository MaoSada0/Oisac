package ru.qq.node.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.qq.common.payload.TaskCatalogPayload;
import ru.qq.common.payload.TaskPayload;
import ru.qq.node.service.MainService;
import ru.qq.node.webclient.DatabaseWebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    private final DatabaseWebClient databaseWebClient;

    @Override
    public boolean processExcel(MultipartFile excelFile, String name, String idOfTeacher) {

        List<TaskPayload> tasksAndAnswers = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(excelFile.getInputStream())) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                // TODO: Добавить проверку
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
}
