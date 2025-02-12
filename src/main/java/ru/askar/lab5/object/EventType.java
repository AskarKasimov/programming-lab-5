package ru.askar.lab5.object;

import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.cli.output.OutputWriter;
import ru.askar.lab5.exception.UserRejectedToFillFieldsException;

public enum EventType {
    E_SPORTS,
    FOOTBALL,
    BASKETBALL,
    OPERA,
    EXPOSITION;

    /**
     * Получить доступные типы в строковом представлении
     */
    public static String getStringValues() {
        StringBuilder result = new StringBuilder();
        for (EventType type : values()) {
            if (!result.isEmpty() && type.ordinal() < values().length) {
                result.append(",");
            }
            result.append(type.name());
        }
        return result.toString();
    }

    /**
     * Создание экземпляра с пользовательским вводом.
     * Если запрашиваемого типа нет, предлагается выбрать ещё раз.
     *
     * @param outputWriter - способ печати ответа
     * @param inputReader  - способ считывания входных данных
     * @return требуемый EventType
     */
    public static EventType createEventType(OutputWriter outputWriter, InputReader inputReader) throws UserRejectedToFillFieldsException {
        outputWriter.write("Выберите тип события (" + getStringValues() + "): ");
        EventType eventType;
        try {
            String value = inputReader.getInputString();
            if (value == null) {
                throw new IllegalArgumentException();
            }
            eventType = valueOf(value);
        } catch (IllegalArgumentException e) {
            outputWriter.writeOnFail("Такого типа нет");
            outputWriter.writeOnWarning("Хотите попробовать еще раз? (y/n): ");
            String answer = inputReader.getInputString();
            if (answer != null && !answer.equals("y")) {
                throw new UserRejectedToFillFieldsException();
            }
            return createEventType(outputWriter, inputReader);
        }
        return eventType;
    }
}