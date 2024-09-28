package ru.qq.node.exception;

public class NotFoundFromDbException extends RuntimeException {
    public NotFoundFromDbException(String message) {
        super(message);
    }
}
