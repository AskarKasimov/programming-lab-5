package ru.askar.lab5.object;

import java.util.Objects;

public class Location {
    private final int x;
    private final Integer y; //Поле не может быть null
    private final long z;
    private final String name; //Поле может быть null

    public Location(int x, Integer y, long z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

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