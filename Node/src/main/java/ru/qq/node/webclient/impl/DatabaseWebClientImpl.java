package ru.qq.node.webclient.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.qq.common.payload.GetTeacherPayload;
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
                .uri("/{id}/uploadTask", nameOfTeacher)
                .bodyValue(taskCatalogPayload)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block());
    }

    @Override
    public boolean createTeacher(GetTeacherPayload teacherPayload) {
        return Boolean.TRUE.equals(webClient.post()
                .uri("/createTeacher")
                .bodyValue(teacherPayload)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block());
    }

    @Override
    public boolean existsTeacher(String username) {
        return Boolean.TRUE.equals(webClient.get()
                .uri(uriBuilder -> uriBuilder
                                .path("/exists")
                                .queryParam("id", username)
                                .build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block());
    }


}
