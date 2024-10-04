package ru.qq.database.exception.teacher;

public class TaskCatalogWithNameAlreadyExistsException extends RuntimeException {
    public TaskCatalogWithNameAlreadyExistsException(String message) {
        super(message);
    }
}
