package ru.askar.lab5.exception;

public class CantChangeOutputWriterException extends RuntimeException {
    public CantChangeOutputWriterException() {
        super("Нельзя поменять поток вывода");
    }
}
