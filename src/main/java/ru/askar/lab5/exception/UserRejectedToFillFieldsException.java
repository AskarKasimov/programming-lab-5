package ru.askar.lab5.exception;

public class UserRejectedToFillFieldsException extends Exception {
    public UserRejectedToFillFieldsException() {
        super("Пользователь отказался заполнять поля");
    }
}
