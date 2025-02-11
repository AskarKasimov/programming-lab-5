package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionManager;

public class InfoCommand extends Command {
    public InfoCommand() {
        super("info", 0);
    }

    @Override
    public void execute(String[] args) {
        outputWriter.writeOnSuccess("Тип коллекции: " + CollectionManager.getInstance().getCollection().getClass());
        outputWriter.writeOnSuccess("Дата инициализации: " + CollectionManager.getInstance().getDateOfCreation());
        outputWriter.writeOnSuccess("Количество элементов: " + CollectionManager.getInstance().getCollection().size());
    }

    @Override
    public String getInfo() {
        return "info - вывести информацию о коллекции (тип, дата инициализации, количество элементов)";
    }
}
