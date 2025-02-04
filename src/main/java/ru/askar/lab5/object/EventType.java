package ru.askar.lab5.object;

import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.cli.output.OutputWriter;

public enum EventType {
    E_SPORTS,
    FOOTBALL,
    BASKETBALL,
    OPERA,
    EXPOSITION;

    public static String getStringValues() {
        StringBuilder result = new StringBuilder();
        for (EventType type : values()) {
            if (!result.isEmpty() && type.ordinal() < values().length - 1) {
                result.append(",");
            }
            result.append(type.name());
        }
        return result.toString();
    }

    public static EventType createEventType(OutputWriter outputWriter, InputReader inputReader) {
        outputWriter.writeOnSuccess("Выберите тип события (" + getStringValues() + "): ");
        EventType eventType;
        try {
            eventType = valueOf(inputReader.getInputString());
        } catch (IllegalArgumentException e) {
            outputWriter.writeOnFail("Такого типа нет. Попробуйте еще раз:");
            return createEventType(outputWriter, inputReader);
        }
        return eventType;
    }
}