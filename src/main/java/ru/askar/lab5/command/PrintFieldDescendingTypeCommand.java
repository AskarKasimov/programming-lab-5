package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionStorage;
import ru.askar.lab5.object.Ticket;

import java.util.Objects;

public class PrintFieldDescendingTypeCommand extends Command {
    public PrintFieldDescendingTypeCommand() {
        super("print_field_descending_type", 0);
    }

    @Override
    public void execute(String[] args) {
        CollectionStorage.getInstance().getCollection().values().stream()
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
