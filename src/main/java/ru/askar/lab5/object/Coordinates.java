package ru.askar.lab5.object;

public class Coordinates {
    private final double x; //Значение поля должно быть больше -645
    private final Long y; //Поле не может быть null

    public Coordinates(double x, Long y) {
        if (x <= -645) {
            throw new IllegalArgumentException("x must be greater than -645");
        }
        if (y == null) {
            throw new IllegalArgumentException("y cannot be null");
        }
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Координаты" +
                ": x=" + x +
                ", y=" + y;
    }
}