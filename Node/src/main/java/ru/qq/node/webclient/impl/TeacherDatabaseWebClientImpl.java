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
import ru.qq.node.webclient.TeacherDatabaseWebClient;

@Component
@RequiredArgsConstructor
public class TeacherDatabaseWebClientImpl implements TeacherDatabaseWebClient {

    private final WebClient webClient;

    @Override
    public boolean saveTasks(TaskCatalogPayload taskCatalogPayload, String nameOfTeacher) {
        return Boolean.TRUE.equals(webClient.post()
                .uri("/teacher/{nickname}/tasks/upload", nameOfTeacher)
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
                .uri("/teacher")
                .bodyValue(teacherPayload)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> handleClientResponse(clientResponse, body, teacherPayload.nickname())))
                .toBodilessEntity()
                .map(response -> response.getStatusCode().is2xxSuccessful())
                .block());
    }

    @Override
    public boolean existsTeacher(String nameOfTeacher) {

        return Boolean.TRUE.equals(webClient.get()
                .uri("/teacher/{nameOfTeacher}", nameOfTeacher)
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
                .uri("/teacher/{nameOfTeacher}/tasks/names", nameOfTeacher)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> handleClientResponse(clientResponse, body, nameOfTeacher)))
                .bodyToMono(String[].class)
                .block();
    }

    private Mono<Throwable> handleClientResponse(ClientResponse clientResponse, String body, String nickname) {
        if (clientResponse.statusCode() == HttpStatus.NOT_FOUND) {
            return Mono.error(new NotFoundFromDbException(getTextForErr(body, nickname)));
        }

        if (clientResponse.statusCode() == HttpStatus.CONFLICT) {
            return Mono.error(new ConflictFromDbException(getTextForErr(body, nickname)));
        }

        return clientResponse.createException().flatMap(Mono::error);
    }

    private String getTextForErr(String body, String nickname){
        return "Err with: " + nickname + ". Response body{" + body + "}";
    }


}
