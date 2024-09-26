package ru.qq.node.webclient;

import ru.qq.common.payload.TaskCatalogPayload;


public interface DatabaseWebClient {
    boolean saveTasks(TaskCatalogPayload taskCatalogPayload, String nameOfTeacher);
}
