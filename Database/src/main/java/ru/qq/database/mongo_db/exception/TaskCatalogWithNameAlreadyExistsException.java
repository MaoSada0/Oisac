package ru.qq.database.mongo_db.exception;

public class TaskCatalogWithNameAlreadyExistsException extends RuntimeException {
    public TaskCatalogWithNameAlreadyExistsException(String message) {
        super(message);
    }
}
