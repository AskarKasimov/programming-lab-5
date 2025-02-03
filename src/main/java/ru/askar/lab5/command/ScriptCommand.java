package ru.askar.lab5.command;

import ru.askar.lab5.exception.NoSuchIdException;

import java.io.IOException;

public class ScriptCommand extends Command {
    public ScriptCommand() {
        super("execute_script");
    }

    @Override
    public void execute(String[] args) throws NoSuchIdException, IOException {

    }

    @Override
    public String getInfo() {
        return "execute_script file_name - считать и исполнить скрипт из указанного файла";
    }
}
