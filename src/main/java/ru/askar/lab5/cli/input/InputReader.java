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
    private final BufferedReader bufferedReader;

    public InputReader(CommandExecutor commandExecutor, CommandParser commandParser, BufferedReader bufferedReader) {
        this.commandExecutor = commandExecutor;
        this.commandParser = commandParser;
        this.bufferedReader = bufferedReader;
    }

    public String getInputString() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new IllegalArgumentException("Ошибка ввода");
        }
    }

    public float getInputFloat() {
        try {
            return Float.parseFloat(bufferedReader.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Требуется число с точкой");
        } catch (IOException e) {
            throw new IllegalArgumentException("Ошибка ввода");
        }
    }

    public void process() throws IOException {
        String line;
        System.out.print("> ");
        while ((line = bufferedReader.readLine()) != null) {
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
                     IOException | NoSuchKeyException | IllegalArgumentException e) {
                commandExecutor.getOutputWriter().writeOnFail(e.getMessage());
            } catch (ExitCLIException e) {
                break;
            } finally {
                System.out.print("> ");
            }
        }
    }
}
