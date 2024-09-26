package ru.qq.node.webclient.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.qq.common.payload.TaskCatalogPayload;
import ru.qq.node.webclient.DatabaseWebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseWebClientImpl implements DatabaseWebClient {

    private final WebClient webClient;

    @Override
    public boolean saveTasks(TaskCatalogPayload taskCatalogPayload, String nameOfTeacher) {
        return Boolean.TRUE.equals(webClient.post()
                .uri("/db/api/v1/{id}/uploadTask", nameOfTeacher)
                .bodyValue(taskCatalogPayload)
                .retrieve()
                .toBodilessEntity()
                .map(response -> response.getStatusCode().is2xxSuccessful())
                .block());
    }
}
