package ru.askar.lab5.cli;

import ru.askar.lab5.cli.output.OutputWriter;
import ru.askar.lab5.command.Command;
import ru.askar.lab5.exception.NoSuchCommandException;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Класс для аккумулирования команд и предоставления к ним доступа.
 */
public class CommandExecutor {
    private final OutputWriter outputWriter;
    private final LinkedHashMap<String, Command> commands = new LinkedHashMap<>();
    private boolean scriptMode;

    /**
     * Создание реестра команд с регистрацией способа вывода.
     *
     * @param outputWriter - куда выводятся ответы CLI.
     */
    public CommandExecutor(OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
        this.scriptMode = false;
    }

    public CommandExecutor(OutputWriter outputWriter, boolean scriptMode) {
        this(outputWriter);
        this.scriptMode = scriptMode;
    }

    public void setOutputWriterToCommands(OutputWriter outputWriter) {
        commands.forEach((name, command) -> command.setOutputWriter(outputWriter));
    }

    public OutputWriter getOutputWriter() {
        return outputWriter;
    }

    /**
     * Добавление команды.
     *
     * @param command - команда
     */
    public void register(Command command) {
        command.setOutputWriter(this.outputWriter);
        command.setScriptMode(this.scriptMode);
        commands.put(command.getName(), command);
    }

    /**
     * Получить команду по названию
     *
     * @param name - название команды
     * @return экземпляр команды
     * @throws NoSuchCommandException - если нет команды с таким названием
     */
    public Command getCommand(String name) throws NoSuchCommandException {
        Command command = commands.get(name);
        if (command == null) {
            throw new NoSuchCommandException(name);
        }
        return commands.get(name);
    }

    /**
     * Копия экземпляра мапы со всеми доступными командами
     */
    public Map<String, Command> getAllCommands() {
        return new LinkedHashMap<>(commands);
    }
}