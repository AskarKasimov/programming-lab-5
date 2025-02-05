package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionStorage;
import ru.askar.lab5.object.Event;
import ru.askar.lab5.object.Ticket;

import java.util.Objects;

public class PrintFieldAscendingEventCommand extends Command {
    public PrintFieldAscendingEventCommand() {
        super("print_field_ascending_event", 0);
    }

    @Override
    public void execute(String[] args) {
        CollectionStorage.getInstance().getCollection().values().stream()
                .map(Ticket::getEvent)
                .filter(Objects::nonNull)
                .sorted(Event::compareTo)
                .forEach(e -> outputWriter.writeOnSuccess(e.toString()));
    }

    @Override
    public String getInfo() {
        return "print_field_ascending_event - вывести значения поля event всех элементов в порядке возрастания";
    }
}
