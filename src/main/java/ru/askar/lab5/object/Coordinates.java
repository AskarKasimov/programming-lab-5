package ru.askar.lab5.object;

public class Coordinates {
    private double x; //Значение поля должно быть больше -645
    private Long y; //Поле не может быть null

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

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }
}