package ru.qq.node.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.reactive.function.client.WebClient;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Value("${project.database.api}")
    private String DATABASE_API;

    @Bean("web")
    //@Scope("prototype")
    public WebClient webClient(){
        return WebClient.builder()
                .baseUrl(DATABASE_API)
                .build();
    }
}
