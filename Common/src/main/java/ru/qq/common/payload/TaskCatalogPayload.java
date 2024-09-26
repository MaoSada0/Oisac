package ru.qq.common.payload;

import java.util.List;

public record TaskCatalogPayload(String name, List<TaskPayload> tasks){
}
