package ru.askar.lab5.exception;

public class NoSuchCommandException extends Exception {
    public NoSuchCommandException(String command) {
        super("Нет такой команды: " + command);
    }
}
