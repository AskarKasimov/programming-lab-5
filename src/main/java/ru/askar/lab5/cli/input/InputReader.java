package ru.askar.lab5.cli.input;

import ru.askar.lab5.cli.CommandExecutor;
import ru.askar.lab5.cli.CommandParser;
import ru.askar.lab5.cli.ParsedCommand;
import ru.askar.lab5.exception.InvalidCommandException;
import ru.askar.lab5.exception.NoSuchCommandException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader {
    public void process(CommandExecutor commandExecutor, CommandParser commandParser) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            commandExecutor.getOutputWriter().writeOnSuccess("CLI запущен. Введите команду (или 'exit' для выхода):");

            String line;
            System.out.print("> ");
            while ((line = reader.readLine()) != null) {
                ParsedCommand parsedCommand;
                try {
                    parsedCommand = commandParser.parse(line.split(" "));
                } catch (InvalidCommandException e) {
                    commandExecutor.getOutputWriter().writeOnFail(e.getMessage());
                    continue;
                }
                if (parsedCommand.name().equals("exit")) {
                    break;
                }

                try {
                    commandExecutor.getCommand(line).execute(parsedCommand.args());
                } catch (NoSuchCommandException e) {
                    commandExecutor.getOutputWriter().writeOnFail(e.getMessage());
                }
                System.out.print("> ");
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении ввода: " + e.getMessage());
        }
    }
}
