package ru.askar.lab5.command;

import ru.askar.lab5.cli.CommandExecutor;
import ru.askar.lab5.cli.CommandParser;
import ru.askar.lab5.cli.input.InputReader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScriptCommand extends Command {
    private final CommandExecutor commandExecutor;
    private final CommandParser commandParser;

    public ScriptCommand(CommandExecutor commandExecutor, CommandParser commandParser) {
        super("execute_script", 1);
        this.commandExecutor = commandExecutor;
        this.commandParser = commandParser;
    }

    @Override
    public void execute(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(args[0]);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        InputReader inputReader = new InputReader(commandExecutor, commandParser);
        inputReader.process(bufferedReader);
    }

    @Override
    public String getInfo() {
        return "execute_script file_name - считать и исполнить скрипт из указанного файла";
    }
}
