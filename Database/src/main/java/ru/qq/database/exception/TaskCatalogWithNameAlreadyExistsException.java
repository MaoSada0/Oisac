package ru.qq.database.exception;

public class TaskCatalogWithNameAlreadyExistsException extends RuntimeException {
    public TaskCatalogWithNameAlreadyExistsException(String message) {
        super(message);
    }
}
