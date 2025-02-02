package ru.askar.lab5.command;

import ru.askar.lab5.cli.output.OutputWriter;
import ru.askar.lab5.exception.CantChangeOutputWriterException;

public abstract class Command {
    protected final String name;
    protected OutputWriter outputWriter;

    public Command(String name) {
        this.name = name;
    }

    public abstract void execute(String[] args); // Выполнить команду

    public abstract String getInfo(); // Получить информацию о команде

    public String getName() {
        return name;
    }

    public void setOutputWriter(OutputWriter newOutputWriter) {
        if (outputWriter != null) throw new CantChangeOutputWriterException();
        outputWriter = newOutputWriter;
    }
}