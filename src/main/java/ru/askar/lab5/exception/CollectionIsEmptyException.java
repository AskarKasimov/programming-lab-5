package ru.askar.lab5.exception;

public class CollectionIsEmptyException extends Exception {
    public CollectionIsEmptyException() {
        super("Коллекция пуста");
    }
}
