package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionStorage;
import ru.askar.lab5.object.Coordinates;
import ru.askar.lab5.object.Ticket;
import ru.askar.lab5.object.TicketType;

public class InsertCommand extends Command {
    public InsertCommand() {
        super("insert", 1);
    }

    @Override
    public void execute(String[] args) {
        Ticket ticket = new Ticket("arr", new Coordinates(1f, 1f), 1, TicketType.VIP, null);
        CollectionStorage.getInstance().getCollection().put(Long.parseLong(args[0]), ticket);
        outputWriter.writeOnSuccess("Элемент добавлен в коллекцию");
    }

    @Override
    public String getInfo() {
        return "insert null {element} - добавить новый элемент с заданным ключом";
    }
}
