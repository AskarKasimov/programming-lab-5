package ru.askar.lab5.cli.input;

import ru.askar.lab5.cli.CommandExecutor;
import ru.askar.lab5.cli.CommandParser;
import ru.askar.lab5.cli.ParsedCommand;
import ru.askar.lab5.exception.InvalidCommandException;
import ru.askar.lab5.exception.NoSuchCommandException;
import ru.askar.lab5.exception.NoSuchIdException;

import java.io.BufferedReader;
import java.io.IOException;

public class InputReader {
    private final CommandExecutor commandExecutor;
    private final CommandParser commandParser;

    public InputReader(CommandExecutor commandExecutor, CommandParser commandParser) {
        this.commandExecutor = commandExecutor;
        this.commandParser = commandParser;
    }

    public void process(BufferedReader reader) throws IOException {
        String line;
        System.out.print("> ");
        while ((line = reader.readLine()) != null) {
            try {
                executeLine(line);
            } catch (InvalidCommandException e) {
                commandExecutor.getOutputWriter().writeOnFail(e.getMessage());
                continue;
            } catch (IOException e) {
                break;
            }
            System.out.print("> ");
        }
    }

    private void executeLine(String line) throws IOException, InvalidCommandException {
        ParsedCommand parsedCommand;
        parsedCommand = commandParser.parse(line.split(" "));
        if (parsedCommand.name().equals("exit")) {
            throw new IOException("Команда выхода");
        }

        try {
            commandExecutor.getCommand(parsedCommand.name()).execute(parsedCommand.args());
        } catch (NoSuchCommandException | NoSuchIdException e) {
            commandExecutor.getOutputWriter().writeOnFail(e.getMessage());
        }
    }
}
