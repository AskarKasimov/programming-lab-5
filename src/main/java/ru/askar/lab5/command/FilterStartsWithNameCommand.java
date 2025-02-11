package ru.askar.lab5.command;

import ru.askar.lab5.collection.CollectionManager;

public class FilterStartsWithNameCommand extends Command {
    public FilterStartsWithNameCommand() {
        super("filter_starts_with_name", 1);
    }

    @Override
    public void execute(String[] args) {
        CollectionManager.getInstance().getCollection().values().stream()
                .filter(t -> t.getName().startsWith(args[0]))
                .forEach(t -> outputWriter.writeOnSuccess(t.toString()));
    }

    @Override
    public String getInfo() {
        return "filter_starts_with_name name - вывести элементы, значение поля name которых начинается с заданной подстроки";
    }
}
