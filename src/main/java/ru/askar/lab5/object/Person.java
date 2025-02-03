package ru.askar.lab5.object;

import java.util.Objects;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String passportID; //Строка не может быть пустой, Длина строки должна быть не меньше 6, Поле не может быть null
    private Color hairColor; //Поле не может быть null
    private Country nationality; //Поле может быть null
    private Location location; //Поле не может быть null

    public Person(String name, String passportID, Color hairColor, Country nationality, Location location) {
        setName(name);
        setPassportID(passportID);
        setHairColor(hairColor);
        setNationality(nationality);
        setLocation(location);
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
                ", национальность - " + nationality +
                ", местоположение - " + location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть null или пустым");
        }
        this.name = name;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        if (passportID == null || passportID.length() < 6) {
            throw new IllegalArgumentException("Паспорт не может быть null, пустым или иметь длину меньше 6");
        }
        this.passportID = passportID;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public void setHairColor(Color hairColor) {
        if (hairColor == null) {
            throw new IllegalArgumentException("Цвет волос не может быть null");
        }
        this.hairColor = hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("Местоположение не может быть null");
        }
        this.location = location;
    }
}