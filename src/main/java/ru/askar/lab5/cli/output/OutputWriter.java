package ru.askar.lab5.cli.output;

/**
 * Описание метода вывода ответов CLI.
 */
public interface OutputWriter {
    String ANSI_RED = "\u001B[31m";
    String ANSI_GREEN = "\u001B[32m";
    String ANSI_RESET = "\u001B[0m";

    /**
     * Печать успешного результата
     *
     * @param message - сообщение
     */
    void writeOnSuccess(String message);

    /**
     * Печать ошибки
     *
     * @param message - сообщение
     */
    void writeOnFail(String message);
}
