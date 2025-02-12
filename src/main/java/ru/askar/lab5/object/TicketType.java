package ru.askar.lab5.object;

import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.cli.output.OutputWriter;
import ru.askar.lab5.exception.UserRejectedToFillFieldsException;

public enum TicketType {
    VIP,
    USUAL,
    BUDGETARY,
    CHEAP;

    /**
     * Получить доступные типы в строковом представлении
     */
    public static String getStringValues() {
        StringBuilder result = new StringBuilder();
        for (TicketType type : values()) {
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
     * @return требуемый TicketType
     */
    public static TicketType createTicketType(OutputWriter outputWriter, InputReader inputReader) throws UserRejectedToFillFieldsException {
        outputWriter.write("Выберите тип билета (" + getStringValues() + "): ");
        TicketType ticketType;
        try {
            String value = inputReader.getInputString();
            if (value == null) {
                throw new IllegalArgumentException();
            }
            ticketType = valueOf(value);
        } catch (IllegalArgumentException e) {
            outputWriter.writeOnFail("Такого типа нет");
            outputWriter.writeOnWarning("Хотите попробовать еще раз? (y/n): ");
            String answer = inputReader.getInputString();
            if (answer != null && !answer.equals("y")) {
                throw new UserRejectedToFillFieldsException();
            }
            return createTicketType(outputWriter, inputReader);
        }
        return ticketType;
    }
}
