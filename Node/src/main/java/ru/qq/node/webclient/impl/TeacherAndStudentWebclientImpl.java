package ru.qq.node.webclient.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.qq.node.exception.ConflictFromDbException;
import ru.qq.node.exception.NotFoundFromDbException;
import ru.qq.node.webclient.TeacherAndStudentWebclient;

@Component
@RequiredArgsConstructor
public class TeacherAndStudentWebclientImpl implements TeacherAndStudentWebclient {

    private final WebClient webClient;

    @Override
    public boolean bindTeacherAndStudent(String teacherNickname, String studentNickname) {

        return Boolean.TRUE.equals(webClient.post()
                .uri((uriBuilder -> uriBuilder
                        .path("/teachers-students/bind")
                        .queryParam("teacherNickname", teacherNickname)
                        .queryParam("studentNickname", studentNickname)
                        .build()))
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
