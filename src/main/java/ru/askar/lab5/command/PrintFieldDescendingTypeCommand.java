package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionManager;
import ru.askar.lab5.object.Ticket;

import java.util.Objects;

public class PrintFieldDescendingTypeCommand extends Command {
    private final CollectionManager collectionManager;

    public PrintFieldDescendingTypeCommand(CollectionManager collectionManager) {
        super("print_field_descending_type", 0);
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.getCollection().values().stream()
                .map(Ticket::getType)
                .filter(Objects::nonNull)
                .sorted()
                .forEach(e -> outputWriter.writeOnSuccess(e.toString()));
    }

    @Override
    public String getInfo() {
        return "print_field_descending_type - вывести значения поля type всех элементов в порядке убывания";
    }
}
