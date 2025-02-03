package ru.askar.lab5.command;

public class ScriptCommand extends Command {
    public ScriptCommand() {
        super("execute_script");
    }

    @Override
    public void execute(String[] args) {

    }

    @Override
    public String getInfo() {
        return "execute_script file_name - считать и исполнить скрипт из указанного файла";
    }
}
