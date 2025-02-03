package ru.askar.lab5.command;

import ru.askar.lab5.exception.NoSuchIdException;

import java.io.IOException;

public class UpdateCommand extends Command {
    public UpdateCommand() {
        super("update");
    }

    @Override
    public void execute(String[] args) throws NoSuchIdException, IOException {

    }

    @Override
    public String getInfo() {
        return "update id {element} - обновить значение элемента коллекции, id которого равен заданному";
    }
}
