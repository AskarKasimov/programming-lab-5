package ru.askar.lab5.command;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.HorizontalAlign;
import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.collection.CollectionManager;
import ru.askar.lab5.object.Ticket;

import java.util.Arrays;

public class ShowCommand extends Command {
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager, InputReader inputReader) {
        super("show", 0, inputReader);
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] args) {
        outputWriter.writeln(AsciiTable.getTable(collectionManager.getCollection().values(), Arrays.asList(
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
        return "show - вывести все элементы коллекции в строковом представлении";
    }
}
