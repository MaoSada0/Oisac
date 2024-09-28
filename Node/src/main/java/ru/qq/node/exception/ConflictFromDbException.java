package ru.qq.node.exception;

public class ConflictFromDbException extends RuntimeException {
  public ConflictFromDbException(String message) {
    super(message);
  }
}
