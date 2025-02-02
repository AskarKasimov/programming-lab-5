package ru.askar.lab5.object;

public class MusicBand {
    private static long nextId = 1; // Для автоинкремента
    private final long id; //Значение поля должн обыть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Coordinates coordinates; //Поле не может быть null
    private final java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final int numberOfParticipants; //Значение поля должно быть больше 0
    private final int albumsCount; //Значение поля должно быть больше 0
    private final MusicGenre genre; //Поле не может быть null
    private final Person frontMan; //Поле может быть null

    public MusicBand(String name, Coordinates coordinates, int numberOfParticipants, int albumsCount, MusicGenre genre, Person frontMan) {
        this.id = nextId;
        nextId++;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = java.time.LocalDateTime.now();
        this.numberOfParticipants = numberOfParticipants;
        this.albumsCount = albumsCount;
        this.genre = genre;
        this.frontMan = frontMan;
    }

    public long getId() {
        return this.id;
    }

    public int getAlbumsCount() {
        return albumsCount;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public Person getFrontMan() {
        return frontMan;
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
}