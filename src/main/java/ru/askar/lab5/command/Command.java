package ru.askar.lab5.command;

import ru.askar.lab5.cli.output.OutputWriter;
import ru.askar.lab5.exception.CantChangeOutputWriterException;
import ru.askar.lab5.exception.CollectionIsEmptyException;
import ru.askar.lab5.exception.ExitCLIException;
import ru.askar.lab5.exception.NoSuchIdException;

import java.io.IOException;

public abstract class Command {
    protected final int argsCount;
    protected final String name;
    protected OutputWriter outputWriter;

    public Command(String name, int argsCount) {
        this.name = name;
        this.argsCount = argsCount;
    }

    public abstract void execute(String[] args) throws NoSuchIdException, IOException, CollectionIsEmptyException, ExitCLIException;

    public abstract String getInfo(); // Получить информацию о команде

    public String getName() {
        return name;
    }

    public int getArgsCount() {
        return argsCount;
    }

    public void setOutputWriter(OutputWriter newOutputWriter) {
        if (outputWriter != null) throw new CantChangeOutputWriterException();
        outputWriter = newOutputWriter;
    }
}