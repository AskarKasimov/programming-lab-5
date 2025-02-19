package ru.askar.lab5.exception;

import ru.askar.lab5.cli.output.OutputWriter;

public class InvalidInputFieldException extends Exception {
    public InvalidInputFieldException(String message) {
        super(OutputWriter.ANSI_RED + message + OutputWriter.ANSI_RESET);
    }
}
