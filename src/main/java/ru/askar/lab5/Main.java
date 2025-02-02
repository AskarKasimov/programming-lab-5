package ru.askar.lab5;


import ru.askar.lab5.cli.CommandExecutor;
import ru.askar.lab5.cli.CommandParser;
import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.cli.output.OutputWriter;
import ru.askar.lab5.cli.output.Stdout;
import ru.askar.lab5.command.AppendCommand;
import ru.askar.lab5.command.HelpCommand;
import ru.askar.lab5.command.InfoCommand;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "CP1251"));

        OutputWriter outputWriter = new Stdout();
        CommandExecutor commandExecutor = new CommandExecutor(outputWriter);
        CommandParser commandParser = new CommandParser();


        commandExecutor.register(new InfoCommand());
        commandExecutor.register(new HelpCommand(commandExecutor));
        commandExecutor.register(new AppendCommand());

        InputReader inputReader = new InputReader();
        inputReader.process(commandExecutor, commandParser);
    }
}