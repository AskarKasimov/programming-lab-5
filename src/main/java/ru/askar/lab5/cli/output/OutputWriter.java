package ru.askar.lab5.cli.output;

public interface OutputWriter {
    String ANSI_RED = "\u001B[31m";
    String ANSI_GREEN = "\u001B[32m";
    String ANSI_RESET = "\u001B[0m";

    void writeOnSuccess(String message);

    void writeOnFail(String message);
}
