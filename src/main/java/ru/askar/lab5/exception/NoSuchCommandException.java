package ru.askar.lab5.exception;

public class NoSuchCommandException extends Exception {
    public NoSuchCommandException() {
        super("Нет такой команды");
    }
}
