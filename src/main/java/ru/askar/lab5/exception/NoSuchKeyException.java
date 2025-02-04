package ru.askar.lab5.exception;

public class NoSuchKeyException extends Exception {
    public NoSuchKeyException() {
        super("Нет элемента с таким ключом");
    }
}
