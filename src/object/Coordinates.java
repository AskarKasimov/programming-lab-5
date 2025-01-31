package object;

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
}