package ru.askar.lab5.command;

import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.collection.CollectionManager;
import ru.askar.lab5.exception.InvalidInputFieldException;
import ru.askar.lab5.object.Event;
import ru.askar.lab5.object.Ticket;

public class ReplaceIfGreaterCommand extends Command {
    private final CollectionManager collectionManager;
    private final InputReader inputReader;

    public ReplaceIfGreaterCommand(CollectionManager collectionManager, InputReader inputReader) {
        super("replace_if_greater", 3);
        this.inputReader = inputReader;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) throws InvalidInputFieldException {
        Long id = Long.parseLong(args[0]);
        Ticket oldTicket = collectionManager.getCollection().get(id);
        if (oldTicket == null) {
            outputWriter.writeOnFail("Элемент с таким id не найден");
            return;
        }

        Ticket newTicket = Ticket.createTicket(outputWriter, inputReader, null, args[0], Long.parseLong(args[1]));
        Ticket.setNextId(newTicket.getId() - 1);
        if (newTicket.getEvent() != null) Event.setNextId(newTicket.getEvent().getId() - 1);

        if (oldTicket.compareTo(newTicket) < 0) {
            collectionManager.getCollection().put(id, newTicket);
            outputWriter.writeOnSuccess("Элемент обновлен");
        } else {
            outputWriter.writeOnFail("Новое значение не больше старого");
        }
    }

    @Override
    public String getInfo() {
        return "replace_if_greater id name price - заменить значение по ключу, если новое значение больше старого";
    }
}
