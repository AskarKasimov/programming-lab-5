package ru.askar.lab5.command;

import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.collection.CollectionManager;
import ru.askar.lab5.object.Ticket;

public class UpdateCommand extends Command {
    private final InputReader inputReader;

    public UpdateCommand(InputReader inputReader) {
        super("update", 3);
        this.inputReader = inputReader;
    }

    @Override
    public void execute(String[] args) {
        Long id = Long.parseLong(args[0]);
        Ticket oldTicket = CollectionManager.getInstance().getCollection().get(id);
        if (oldTicket == null) {
            outputWriter.writeOnFail("Элемент с таким id не найден");
            return;
        }
        String name = args[1];
        long price;
        try {
            price = Long.parseLong(args[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("В поле price требуется число");
        }
        outputWriter.writeOnSuccess("Хотите изменить прочие данные? (y/n): ");
        if (inputReader.getInputString().equals("y")) {
            Ticket newTicket = Ticket.createTicket(outputWriter, inputReader, id, name, price);
            CollectionManager.getInstance().getCollection().put(id, newTicket);
        } else {
            oldTicket.setName(name);
            oldTicket.setPrice(price);
        }
        outputWriter.writeOnSuccess("Элемент обновлен");
    }

    @Override
    public String getInfo() {
        return "update id name price - обновить значение элемента коллекции, id которого равен заданному";
    }
}
