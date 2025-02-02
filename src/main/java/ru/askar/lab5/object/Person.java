package ru.askar.lab5.object;

import java.util.Objects;

public class Person {
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final String passportID; //Строка не может быть пустой, Длина строки должна быть не меньше 6, Поле не может быть null
    private final Color hairColor; //Поле не может быть null
    private final Country nationality; //Поле может быть null
    private final Location location; //Поле не может быть null

    public Person(String name, String passportID, Color hairColor, Country nationality, Location location) {
        this.name = name;
        this.passportID = passportID;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(passportID, person.passportID) && hairColor == person.hairColor && nationality == person.nationality && Objects.equals(location, person.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passportID, hairColor, nationality, location);
    }

    @Override
    public String toString() {
        return "Человек" +
                ": имя - " + name +
                ", паспорт - " + passportID +
                ", цвет волос - " + hairColor +
                ", национальность -" + nationality +
                ", местоположение - " + location;
    }
}