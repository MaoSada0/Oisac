package ru.qq.node.webclient.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.qq.common.payload.TeacherPayload;
import ru.qq.common.payload.TaskCatalogPayload;
import ru.qq.node.exception.NotFoundFromDbException;
import ru.qq.node.exception.ConflictFromDbException;
import ru.qq.node.webclient.DatabaseWebClient;

@Component
@RequiredArgsConstructor
public class DatabaseWebClientImpl implements DatabaseWebClient {

    private final WebClient webClient;

    @Override
    public boolean saveTasks(TaskCatalogPayload taskCatalogPayload, String nameOfTeacher) {
        return Boolean.TRUE.equals(webClient.post()
                .uri("/{id}/uploadTasks", nameOfTeacher)
                .bodyValue(taskCatalogPayload)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> handleClientResponse(clientResponse, body, nameOfTeacher)))
                .toBodilessEntity()
                .map(response -> response.getStatusCode().is2xxSuccessful())
                .block());
    }

    @Override
    public boolean createTeacher(TeacherPayload teacherPayload) {
        return Boolean.TRUE.equals(webClient.post()
                .uri("/createTeacher")
                .bodyValue(teacherPayload)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> handleClientResponse(clientResponse, body, teacherPayload.name())))
                .toBodilessEntity()
                .map(response -> response.getStatusCode().is2xxSuccessful())
                .block());
    }

    @Override
    public boolean existsTeacher(String nameOfTeacher) {
        return Boolean.TRUE.equals(webClient.get()
                .uri(uriBuilder -> uriBuilder
                                .path("/exists")
                                .queryParam("id", nameOfTeacher)
                                .build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> handleClientResponse(clientResponse, body, nameOfTeacher)))
                .toBodilessEntity()
                .map(response -> response.getStatusCode().is2xxSuccessful())
                .block());
    }

    @Override
    public String[] getNamesOfTasks(String nameOfTeacher) {
        return webClient.get()
                .uri("/{id}/getNamesOfTasks", nameOfTeacher)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> handleClientResponse(clientResponse, body, nameOfTeacher)))
                .bodyToMono(String[].class)
                .block();
    }

    private Mono<Throwable> handleClientResponse(ClientResponse clientResponse, String body, String nameOfTeacher) {
        if (clientResponse.statusCode() == HttpStatus.NOT_FOUND) {
            return Mono.error(new NotFoundFromDbException(getTextForErr(body, nameOfTeacher)));
        }

        if (clientResponse.statusCode() == HttpStatus.CONFLICT) {
            return Mono.error(new ConflictFromDbException(getTextForErr(body, nameOfTeacher)));
        }

        return clientResponse.createException().flatMap(Mono::error);
    }

    private String getTextForErr(String body, String nameOfTeacher){
        return "Err with: " + nameOfTeacher + ". Response body{" + body + "}";
    }


}
