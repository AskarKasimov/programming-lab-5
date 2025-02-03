package ru.askar.lab5.command;

import java.io.IOException;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit");
    }

    @Override
    public void execute(String[] args) throws IOException {
        outputWriter.writeOnSuccess("Выход");
        throw new IOException("Выход");
    }

    @Override
    public String getInfo() {
        return "exit - завершить программу (без сохранения в файл)";
    }
}
