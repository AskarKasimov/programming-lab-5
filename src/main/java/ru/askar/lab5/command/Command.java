package ru.askar.lab5.command;

public interface Command {
    void execute(String[] args); // Выполнить команду
    String getInfo(); // Получить информацию о команде
    String getName(); // Получить имя команды
}