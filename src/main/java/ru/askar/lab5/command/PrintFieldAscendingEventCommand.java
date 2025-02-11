package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionManager;
import ru.askar.lab5.object.Event;
import ru.askar.lab5.object.Ticket;

import java.util.Objects;

public class PrintFieldAscendingEventCommand extends Command {
    private final CollectionManager collectionManager;

    public PrintFieldAscendingEventCommand(CollectionManager collectionManager) {
        super("print_field_ascending_event", 0);
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        collectionManager.getCollection().values().stream()
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
