package ru.qq.node.webclient.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.qq.common.payload.StudentPayload;
import ru.qq.node.exception.ConflictFromDbException;
import ru.qq.node.exception.NotFoundFromDbException;
import ru.qq.node.webclient.StudentDatabaseWebClient;

@Component
@RequiredArgsConstructor
public class StudentDatabaseWebClientImpl implements StudentDatabaseWebClient {

    private final WebClient webClient;

    @Override
    public boolean existsStudent(String nickname) {
        return Boolean.TRUE.equals(webClient.get()
                .uri("/student/{nickname}", nickname)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> handleClientResponse(clientResponse, body, nickname)))
                .toBodilessEntity()
                .map(response -> response.getStatusCode().is2xxSuccessful())
                .block());
    }

    @Override
    public boolean createStudent(StudentPayload studentPayload) {
        return Boolean.TRUE.equals(webClient.post()
                .uri("/student")
                .bodyValue(studentPayload)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(String.class)
                        .flatMap(body -> handleClientResponse(clientResponse, body, studentPayload.nickname())))
                .toBodilessEntity()
                .map(response -> response.getStatusCode().is2xxSuccessful())
                .block());
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
