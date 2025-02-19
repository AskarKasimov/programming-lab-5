package ru.askar.lab5.exception;

public class InvalidCollectionFileException extends RuntimeException {
    public InvalidCollectionFileException(String message) {
        super(message);
    }
}
