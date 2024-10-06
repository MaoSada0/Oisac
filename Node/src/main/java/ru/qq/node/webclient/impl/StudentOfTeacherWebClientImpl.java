package ru.qq.node.webclient.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.qq.common.payload.AddTaskCatalogToStudentPayload;
import ru.qq.node.exception.ConflictFromDbException;
import ru.qq.node.exception.NotFoundFromDbException;
import ru.qq.node.webclient.StudentOfTeacherWebClient;

@Component
@RequiredArgsConstructor
@Log4j
public class StudentOfTeacherWebClientImpl implements StudentOfTeacherWebClient {

    private final WebClient webClient;

    @Override
    public boolean addTasksToStudent(String teacherNickname, String studentNickname, AddTaskCatalogToStudentPayload payload) {
        return Boolean.TRUE.equals(webClient.post()
                .uri("/teacher/{teacherNickname}/students/{studentNickname}/tasks/add",
                        teacherNickname,
                        studentNickname)
                .bodyValue(payload)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> handleClientResponse(clientResponse, body, teacherNickname, studentNickname)))
                .toBodilessEntity()
                .map(response -> response.getStatusCode().is2xxSuccessful())
                .block());
    }

    private Mono<Throwable> handleClientResponse(ClientResponse clientResponse,
                                                 String body,
                                                 String teacherNickname,
                                                 String studentNickname) {

        if (clientResponse.statusCode() == HttpStatus.NOT_FOUND) {
            return Mono.error(new NotFoundFromDbException(getTextForErr(body, teacherNickname, studentNickname)));
        }

        if (clientResponse.statusCode() == HttpStatus.CONFLICT) {
            return Mono.error(new ConflictFromDbException(getTextForErr(body, teacherNickname, studentNickname)));
        }

        return clientResponse.createException().flatMap(Mono::error);
    }

    private String getTextForErr(String body,
                                 String teacherNickname,
                                 String studentNickname){
        return "Err with: {" + teacherNickname + ", " + studentNickname + "}. Response body{" + body + "}";
    }
}


