package ru.askar.lab5.command;

import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.collection.CollectionManager;
import ru.askar.lab5.exception.InvalidInputFieldException;
import ru.askar.lab5.object.Ticket;

public class RemoveLowerCommand extends Command {
    private final CollectionManager collectionManager;
    private final InputReader inputReader;

    public RemoveLowerCommand(CollectionManager collectionManager, InputReader inputReader) {
        super("remove_lower", 2);
        this.inputReader = inputReader;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws InvalidInputFieldException {
        Ticket ticket = Ticket.createTicket(outputWriter, inputReader, 1L, args[0], Long.parseLong(args[1]), collectionManager.generateNextEventId());
        int oldSize = collectionManager.getCollection().size();
        collectionManager.getCollection().values().removeIf(t -> t.compareTo(ticket) < 0);
        if (oldSize == collectionManager.getCollection().size()) {
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
