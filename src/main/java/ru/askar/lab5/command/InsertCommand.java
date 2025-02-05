package ru.askar.lab5.command;

import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.collection.CollectionStorage;
import ru.askar.lab5.object.Ticket;

public class InsertCommand extends Command {

    InputReader inputReader;

    public InsertCommand(InputReader inputReader) {
        super("insert", 3);
        this.inputReader = inputReader;
    }

    @Override
    public void execute(String[] args) {
        Long id = null;
        if (!args[0].equals("null")) {
            id = Long.parseLong(args[0]);
            if (CollectionStorage.getInstance().getCollection().containsKey(id)) {
                outputWriter.writeOnFail("Такой id уже существует");
                return;
            }
        }
        String name = args[1];
        long price;
        try {
            price = Long.parseLong(args[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("В поле price требуется число");
        }
        Ticket ticket = Ticket.createTicket(outputWriter, inputReader, id, name, price);
        CollectionStorage.getInstance().getCollection().put(ticket.getId(), ticket);
        outputWriter.writeOnSuccess("Элемент добавлен в коллекцию");
    }

    @Override
    public String getInfo() {
        return "insert id?null name price - добавить новый элемент";
    }
}
