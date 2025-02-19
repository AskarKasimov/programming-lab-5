package ru.askar.lab5.command;

import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.exception.ExitCLIException;

public class ExitCommand extends Command {
    public ExitCommand(InputReader inputReader) {
        super("exit", 0, inputReader);
    }

    @Override
    public void execute(String[] args) throws ExitCLIException {
        throw new ExitCLIException();
    }

    @Override
    public String getInfo() {
        return "exit - завершить программу (без сохранения в файл)";
    }
}
