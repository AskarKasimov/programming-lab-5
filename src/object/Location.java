package object;

import java.util.Objects;

public class Location {
    private int x;
    private Integer y; //Поле не может быть null
    private long z;
    private String name; //Поле может быть null

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x && z == location.z && Objects.equals(y, location.y) && Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, name);
    }

    @Override
    public String toString() {
        return "Локация" +
                ": x =" + x +
                ", y =" + y +
                ", z =" + z +
                ", название - " + name;
    }
}