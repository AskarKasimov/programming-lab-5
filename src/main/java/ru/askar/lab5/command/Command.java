package ru.askar.lab5.command;

import ru.askar.lab5.cli.output.OutputWriter;
import ru.askar.lab5.exception.*;

import java.io.IOException;

/**
 * Абстрактный класс для команд
 */
public abstract class Command implements Cloneable {
    protected final int argsCount;
    protected final String name;
    protected OutputWriter outputWriter;
    protected boolean scriptMode = false;

    /**
     * Заполнение имени и количества требуемых аргументов
     *
     * @param name
     * @param argsCount
     */
    public Command(String name, int argsCount) {
        this.name = name;
        this.argsCount = argsCount;
    }

    public void setScriptMode(boolean scriptMode) {
        this.scriptMode = scriptMode;
    }

    /**
     * Выполнение логики команды
     *
     * @param args - аргументы
     * @throws IOException                - ошибка чтения ввода
     * @throws CollectionIsEmptyException - коллекция пуста
     * @throws ExitCLIException           - выход из CLI
     * @throws NoSuchKeyException         - нет такого ключа в коллекции, на который пытается сослаться команда
     */
    public abstract void execute(String[] args) throws IOException, CollectionIsEmptyException, ExitCLIException, NoSuchKeyException, InvalidInputFieldException, UserRejectedToFillFieldsException;

    /**
     * Выдать справку об использовании команды
     */
    public abstract String getInfo();

    public String getName() {
        return name;
    }

    public int getArgsCount() {
        return argsCount;
    }

    /**
     * Задать вывод результата исполнения команды
     *
     * @see OutputWriter
     */
    public void setOutputWriter(OutputWriter newOutputWriter) {
        outputWriter = newOutputWriter;
    }

    @Override
    public Command clone() {
        try {
            return (Command) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}