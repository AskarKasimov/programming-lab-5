package ru.askar.lab5;


import ru.askar.lab5.cli.CommandExecutor;
import ru.askar.lab5.cli.CommandParser;
import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.cli.output.OutputWriter;
import ru.askar.lab5.cli.output.Stdout;
import ru.askar.lab5.command.AppendCommand;
import ru.askar.lab5.command.HelpCommand;
import ru.askar.lab5.command.InfoCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setOut(new PrintStream(System.out, true, "CP1251"));

        OutputWriter outputWriter = new Stdout();
        CommandExecutor commandExecutor = new CommandExecutor(outputWriter);
        CommandParser commandParser = new CommandParser();


        commandExecutor.register(new InfoCommand());
        commandExecutor.register(new HelpCommand(commandExecutor));
        commandExecutor.register(new AppendCommand());

        InputReader inputReader = new InputReader(commandExecutor, commandParser);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        inputReader.process(bufferedReader);
    }
}