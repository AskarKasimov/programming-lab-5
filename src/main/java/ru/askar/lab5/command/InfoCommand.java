package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionStorage;

public class InfoCommand extends Command {
    public InfoCommand() {
        super("info", 0);
    }

    @Override
    public void execute(String[] args) {
        outputWriter.writeOnSuccess("Тип коллекции: " + CollectionStorage.getInstance().getCollection().getClass());
        outputWriter.writeOnSuccess("Дата инициализации: " + CollectionStorage.getInstance().getDateOfCreation());
        outputWriter.writeOnSuccess("Количество элементов: " + CollectionStorage.getInstance().getCollection().size());
    }

    @Override
    public String getInfo() {
        return "info - вывести информацию о коллекции (тип, дата инициализации, количество элементов)";
    }
}
