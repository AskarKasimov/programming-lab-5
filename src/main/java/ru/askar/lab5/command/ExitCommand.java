package ru.askar.lab5.command;

import ru.askar.lab5.exception.ExitCLIException;

import java.io.IOException;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit", 0);
    }

    @Override
    public void execute(String[] args) throws IOException, ExitCLIException {
        throw new ExitCLIException();
    }

    @Override
    public String getInfo() {
        return "exit - завершить программу (без сохранения в файл)";
    }
}
