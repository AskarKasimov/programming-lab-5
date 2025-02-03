package ru.askar.lab5.command;

public class UpdateCommand extends Command {
    public UpdateCommand() {
        super("update");
    }

    @Override
    public void execute(String[] args) {

    }

    @Override
    public String getInfo() {
        return "update id {element} - обновить значение элемента коллекции, id которого равен заданному";
    }
}
