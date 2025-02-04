package ru.askar.lab5.command;

import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.collection.CollectionStorage;
import ru.askar.lab5.object.Event;
import ru.askar.lab5.object.Ticket;

public class ReplaceIfGreaterCommand extends Command {
    private final InputReader inputReader;

    public ReplaceIfGreaterCommand(InputReader inputReader) {
        super("replace_if_greater", 3);
        this.inputReader = inputReader;
    }

    @Override
    public void execute(String[] args) {
        Long id = Long.parseLong(args[0]);
        Ticket oldTicket = CollectionStorage.getInstance().getCollection().get(id);
        if (oldTicket == null) {
            outputWriter.writeOnFail("������� � ����� id �� ������");
            return;
        }

        Ticket newTicket = Ticket.createTicket(outputWriter, inputReader, null, args[0], Long.parseLong(args[1]));
        Ticket.setNextId(newTicket.getId() - 1);
        if (newTicket.getEvent() != null) Event.setNextId(newTicket.getEvent().getId() - 1);

        if (oldTicket.compareTo(newTicket) < 0) {
            CollectionStorage.getInstance().getCollection().put(id, newTicket);
            outputWriter.writeOnSuccess("������� ��������");
        } else {
            outputWriter.writeOnFail("����� �������� �� ������ �������");
        }
    }

    @Override
    public String getInfo() {
        return "replace_if_greater id name price - �������� �������� �� �����, ���� ����� �������� ������ �������";
    }
}
