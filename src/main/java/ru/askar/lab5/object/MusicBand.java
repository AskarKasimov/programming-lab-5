package ru.askar.lab5.object;

import java.time.LocalDateTime;
import java.util.Objects;

public class MusicBand implements Comparable<MusicBand> {
    private static long nextId = 1; // Для автоинкремента
    private final long id; //Значение поля должн обыть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private int numberOfParticipants; //Значение поля должно быть больше 0
    private int albumsCount; //Значение поля должно быть больше 0
    private MusicGenre genre; //Поле не может быть null
    private Person frontMan; //Поле может быть null

    public MusicBand(String name, Coordinates coordinates, int numberOfParticipants, int albumsCount, MusicGenre genre, Person frontMan) {
        this.id = nextId;
        nextId++;
        setName(name);
        setCoordinates(coordinates);
        this.creationDate = LocalDateTime.now();
        setNumberOfParticipants(numberOfParticipants);
        setAlbumsCount(albumsCount);
        setGenre(genre);
        setFrontMan(frontMan);
    }

    @Override
    public int compareTo(MusicBand o) {
        return Long.compare(this.id, o.id);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MusicBand musicBand = (MusicBand) o;
        return id == musicBand.id && numberOfParticipants == musicBand.numberOfParticipants && albumsCount == musicBand.albumsCount && Objects.equals(name, musicBand.name) && Objects.equals(coordinates, musicBand.coordinates) && Objects.equals(creationDate, musicBand.creationDate) && genre == musicBand.genre && Objects.equals(frontMan, musicBand.frontMan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, numberOfParticipants, albumsCount, genre, frontMan);
    }

    @Override
    public String toString() {
        return "Музыкальная группа" +
                ": id=" + id +
                ", название - " + name +
                ", координаты - " + coordinates +
                ", дата создания - " + creationDate +
                ", количество участников - " + numberOfParticipants +
                ", количество альбомов - " + albumsCount +
                ", жанр - " + genre +
                ", фронтмен - " + frontMan;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть null или пустым");
        }
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    private void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Координаты не могут быть null");
        }
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    private void setNumberOfParticipants(int numberOfParticipants) {
        if (numberOfParticipants <= 0) {
            throw new IllegalArgumentException("Количество участников должно быть больше 0");
        }
        this.numberOfParticipants = numberOfParticipants;
    }

    public int getAlbumsCount() {
        return albumsCount;
    }

    private void setAlbumsCount(int albumsCount) {
        if (albumsCount <= 0) {
            throw new IllegalArgumentException("Количество альбомов должно быть больше 0");
        }
        this.albumsCount = albumsCount;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    private void setGenre(MusicGenre genre) {
        if (genre == null) {
            throw new IllegalArgumentException("Жанр не может быть null");
        }
        this.genre = genre;
    }

    public Person getFrontMan() {
        return frontMan;
    }

    public void setFrontMan(Person frontMan) {
        this.frontMan = frontMan;
    }
}