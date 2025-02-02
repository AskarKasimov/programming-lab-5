package ru.askar.lab5.command;

public class InfoCommand implements Command {

    @Override
    public void execute(String[] args) {
    }

    @Override
    public String getInfo() {
        return "info - вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }

    @Override
    public String getName() {
        return "info";
    }
}
