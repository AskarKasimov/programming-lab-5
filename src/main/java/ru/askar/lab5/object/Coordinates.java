package ru.askar.lab5.object;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.cli.output.OutputWriter;
import ru.askar.lab5.exception.InvalidInputFieldException;
import ru.askar.lab5.exception.UserRejectedToFillFieldsException;

import java.math.BigDecimal;
import java.util.Objects;

public class Coordinates {
    private Float x; //Поле не может быть null
    private Float y; //Максимальное значение поля: 654, Поле не может быть null

    @JsonCreator
    public Coordinates(@JsonProperty("x") Float x,
                       @JsonProperty("y") BigDecimal y) throws InvalidInputFieldException {
        setX(x);
        setY(y);
    }

    private Coordinates() {
    }

    /**
     * Создание экземпляра с пользовательским вводом.
     *
     * @param outputWriter - способ печати ответа
     * @param inputReader  - способ считывания входных данных
     * @return - созданный Coordinates
     */
    public static Coordinates createCoordinates(OutputWriter outputWriter, InputReader inputReader, boolean scriptMode) throws UserRejectedToFillFieldsException {
        Coordinates coordinates = new Coordinates();
        outputWriter.write("Ввод координат: ");
        coordinates.requestX(outputWriter, inputReader, scriptMode);
        coordinates.requestY(outputWriter, inputReader, scriptMode);
        return coordinates;
    }

    private void requestX(OutputWriter outputWriter, InputReader inputReader, boolean scriptMode) throws UserRejectedToFillFieldsException {
        Float x;
        do {
            outputWriter.write("Введите x: ");
            try {
                x = inputReader.getInputFloat();
                this.setX(x);
            } catch (InvalidInputFieldException | IllegalArgumentException e) {
                x = null;
                if (scriptMode) {
                    throw new UserRejectedToFillFieldsException();
                }
                outputWriter.writeOnFail(e.getMessage());
                outputWriter.writeOnWarning("Хотите попробовать еще раз? (y/n): ");
                String answer = inputReader.getInputString();
                if (answer != null && !answer.equalsIgnoreCase("y")) {
                    throw new UserRejectedToFillFieldsException();
                }
            }
        } while (x == null);
    }

    private void requestY(OutputWriter outputWriter, InputReader inputReader, boolean scriptMode) throws UserRejectedToFillFieldsException {
        BigDecimal y;
        do {
            outputWriter.write("Введите y: ");
            try {
                y = inputReader.getInputBigDecimal();
                this.setY(y);
            } catch (InvalidInputFieldException | IllegalArgumentException e) {
                y = null;
                if (scriptMode) {
                    throw new UserRejectedToFillFieldsException();
                }
                outputWriter.writeOnFail(e.getMessage());
                outputWriter.writeOnWarning("Хотите попробовать еще раз? (y/n): ");
                String answer = inputReader.getInputString();
                if (answer != null && !answer.equalsIgnoreCase("y")) {
                    throw new UserRejectedToFillFieldsException();
                }
            }
        } while (y == null);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Координаты" +
                ": x=" + x +
                ", y=" + y;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) throws InvalidInputFieldException {
        if (x == null) {
            throw new InvalidInputFieldException("Координата X не может быть null");
        }
        if (x.isInfinite()) {
            throw new InvalidInputFieldException("Координата X слишком большая");
        }
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(BigDecimal y) throws InvalidInputFieldException {
        if (y == null) {
            throw new InvalidInputFieldException("Координата Y не может быть null");
        }
        if (Float.valueOf(y.floatValue()).isInfinite()) {
            throw new InvalidInputFieldException("Координата Y слишком большая");
        }
        if (y.compareTo(new BigDecimal("654.0")) > 0) {
            throw new InvalidInputFieldException("Координата Y не может быть больше 654.0");
        }
        this.y = y.floatValue();
    }
}