package ru.askar.lab5.object;

import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.cli.output.OutputWriter;

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
    public static TicketType createTicketType(OutputWriter outputWriter, InputReader inputReader) {
        outputWriter.writeOnSuccess("Выберите тип билета (" + getStringValues() + "): ");
        TicketType ticketType;
        try {
            ticketType = valueOf(inputReader.getInputString());
        } catch (IllegalArgumentException e) {
            outputWriter.writeOnFail("Такого типа нет. Попробуйте еще раз:");
            return createTicketType(outputWriter, inputReader);
        }
        return ticketType;
    }
}
