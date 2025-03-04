package ru.askar.lab5.command;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.HorizontalAlign;
import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.collection.CollectionManager;
import ru.askar.lab5.object.Ticket;

import java.util.Arrays;
import java.util.List;

public class FilterStartsWithNameCommand extends Command {
    private final CollectionManager collectionManager;

    public FilterStartsWithNameCommand(CollectionManager collectionManager, InputReader inputReader) {
        super("filter_starts_with_name", 1, inputReader);
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        List<Ticket> ticketList = collectionManager.getCollection().values().stream()
                .filter(t -> t.getName().startsWith(args[0]))
                .toList();
        outputWriter.writeln(AsciiTable.getTable(ticketList, Arrays.asList(
                new Column().header("ID").maxWidth(10).headerAlign(HorizontalAlign.CENTER).with(ticket -> String.valueOf(ticket.getId())),
                new Column().header("Название").maxWidth(10).headerAlign(HorizontalAlign.CENTER).with(Ticket::getName),
                new Column().header("Координаты").maxWidth(31).headerAlign(HorizontalAlign.CENTER).with(ticket -> "(" + ticket.getCoordinates().getX() + ", " + ticket.getCoordinates().getY() + ")"),
                new Column().header("Дата создания").maxWidth(31).headerAlign(HorizontalAlign.CENTER).with(ticket -> ticket.getCreationDate().toString()),
                new Column().header("Цена").maxWidth(10).headerAlign(HorizontalAlign.CENTER).with(ticket -> String.valueOf(ticket.getPrice())),
                new Column().header("Тип").maxWidth(10).headerAlign(HorizontalAlign.CENTER).with(ticket -> ticket.getType().name()),
                new Column().header("ID события").maxWidth(10).headerAlign(HorizontalAlign.CENTER).with(ticket -> ticket.getEvent() != null ? String.valueOf(ticket.getEvent().getId()) : "-"),
                new Column().header("Название события").maxWidth(10).headerAlign(HorizontalAlign.CENTER).with(ticket -> ticket.getEvent() != null ? ticket.getEvent().getName() : "-"),
                new Column().header("Описание события").maxWidth(20).headerAlign(HorizontalAlign.CENTER).with(ticket -> ticket.getEvent() != null ? ticket.getEvent().getDescription() : "-"),
                new Column().header("Тип события").maxWidth(10).headerAlign(HorizontalAlign.CENTER).with(ticket -> ticket.getEvent() != null && ticket.getEvent().getEventType() != null ? ticket.getEvent().getEventType().name() : "-")
        )));
    }

    @Override
    public String getInfo() {
        return "filter_starts_with_name name - вывести элементы, значение поля name которых начинается с заданной подстроки";
    }
}
