package ru.askar.lab5.command;

import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.collection.CollectionStorage;
import ru.askar.lab5.object.Event;
import ru.askar.lab5.object.Ticket;

public class RemoveLowerCommand extends Command {
    private final InputReader inputReader;

    public RemoveLowerCommand(InputReader inputReader) {
        super("remove_lower", 2);
        this.inputReader = inputReader;
    }

    @Override
    public void execute(String[] args) {
        Ticket ticket = Ticket.createTicket(outputWriter, inputReader, null, args[0], Long.parseLong(args[1]));
        Ticket.setNextId(ticket.getId() - 1);
        if (ticket.getEvent() != null) Event.setNextId(ticket.getEvent().getId() - 1);
        int oldSize = CollectionStorage.getInstance().getCollection().size();
        CollectionStorage.getInstance().getCollection().values().removeIf(t -> t.compareTo(ticket) < 0);
        if (oldSize == CollectionStorage.getInstance().getCollection().size()) {
            outputWriter.writeOnFail("Элементы не найдены");
            return;
        }
        outputWriter.writeOnSuccess("Элементы удалены");
    }

    @Override
    public String getInfo() {
        return "remove_lower name price - удалить из коллекции все элементы, меньшие, чем заданный";
    }
}
