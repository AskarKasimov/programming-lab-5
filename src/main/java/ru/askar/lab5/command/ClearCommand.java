package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionManager;
import ru.askar.lab5.exception.CollectionIsEmptyException;

public class ClearCommand extends Command {
    public ClearCommand() {
        super("clear", 0);
    }

    @Override
    public void execute(String[] args) throws CollectionIsEmptyException {
        if (CollectionManager.getInstance().getCollection().isEmpty())
            throw new CollectionIsEmptyException();
        else {
            CollectionManager.getInstance().getCollection().clear();
            outputWriter.writeOnSuccess("Коллекция очищена");
        }
    }

    @Override
    public String getInfo() {
        return "clear - очистить коллекцию";
    }
}
