package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionStorage;

public class ShowCommand extends Command {
    public ShowCommand() {
        super("show");
    }

    @Override
    public void execute(String[] args) {
        if (CollectionStorage.getInstance().getCollection().isEmpty())
            outputWriter.writeOnFail("Коллекция пуста");
        else
            CollectionStorage.getInstance().getCollection().forEach(band -> outputWriter.writeOnSuccess(band.toString()));
    }

    @Override
    public String getInfo() {
        return "show - вывести все элементы коллекции в строковом представлении";
    }
}
