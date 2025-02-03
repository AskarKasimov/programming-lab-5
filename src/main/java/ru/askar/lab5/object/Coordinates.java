package ru.askar.lab5.object;

public class Coordinates {
    private double x; //Значение поля должно быть больше -645
    private Long y; //Поле не может быть null

    public Coordinates(double x, Long y) {
        setX(x);
        setY(y);
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
        if (x <= -645) {
            throw new IllegalArgumentException("X должен быть больше -645");
        }
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        if (y == null) {
            throw new IllegalArgumentException("Y не может быть null");
        }
        this.y = y;
    }
}