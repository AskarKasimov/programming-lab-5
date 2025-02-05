package ru.askar.lab5.object;

import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.cli.output.OutputWriter;

import java.util.Objects;

public class Coordinates {
    private Float x; //Поле не может быть null
    private Float y; //Максимальное значение поля: 654, Поле не может быть null

    public Coordinates(Float x, Float y) {
        setX(x);
        setY(y);
    }

    /**
     * Создание экземпляра с пользовательским вводом.
     *
     * @param outputWriter - способ печати ответа
     * @param inputReader  - способ считывания входных данных
     * @return - созданный Coordinates
     */
    public static Coordinates createCoordinates(OutputWriter outputWriter, InputReader inputReader) {
        Coordinates coordinates = new Coordinates((float) 0, (float) 0);
        outputWriter.writeOnSuccess("Ввод координат");

        outputWriter.writeOnSuccess("Введите координату x: ");
        coordinates.setX(inputReader.getInputFloat());

        outputWriter.writeOnSuccess("Введите координату y: ");
        coordinates.setY(inputReader.getInputFloat());

        return coordinates;
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

    public void setX(Float x) {
        if (x == null) {
            throw new IllegalArgumentException("X не может быть null");
        }
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        if (y == null) {
            throw new IllegalArgumentException("Y не может быть null");
        }
        if (y > 654) {
            throw new IllegalArgumentException("Y не может быть больше 654");
        }
        this.y = y;
    }
}