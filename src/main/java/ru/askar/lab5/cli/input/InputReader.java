package ru.askar.lab5.cli.input;

import ru.askar.lab5.cli.CommandExecutor;
import ru.askar.lab5.cli.CommandParser;
import ru.askar.lab5.cli.ParsedCommand;
import ru.askar.lab5.exception.*;

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
                ParsedCommand parsedCommand;
                try {
                    parsedCommand = commandParser.parse(line.split(" "));
                } catch (InvalidCommandException e) {
                    commandExecutor.getOutputWriter().writeOnFail(e.getMessage());
                    continue;
                }
                if (parsedCommand.args().length != commandExecutor.getCommand(parsedCommand.name()).getArgsCount()) {
                    commandExecutor.getOutputWriter().writeOnFail("Неверное количество аргументов: для команды " + parsedCommand.name() + " требуется " + commandExecutor.getCommand(parsedCommand.name()).getArgsCount());
                    continue;
                }
                commandExecutor.getCommand(parsedCommand.name()).execute(parsedCommand.args());
            } catch (CollectionIsEmptyException | NoSuchCommandException | NoSuchIdException |
                     IOException e) {
                commandExecutor.getOutputWriter().writeOnFail(e.getMessage());
            } catch (ExitCLIException e) {
                break;
            } finally {
                System.out.print("> ");
            }
        }
    }
}
