package ru.qq.node;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Node {

    public static void main(String[] args) {
        SpringApplication.run(Node.class, args);
    }

}
