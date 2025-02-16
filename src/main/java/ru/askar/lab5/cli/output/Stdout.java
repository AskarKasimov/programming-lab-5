package ru.askar.lab5.cli.output;

public class Stdout implements OutputWriter {
    @Override
    public void writeOnSuccess(String message) {
        System.out.println(ANSI_GREEN + message + ANSI_RESET);
    }

    @Override
    public void writeOnFail(String message) {
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }
}
