package object;

public class MusicBand {
    private static long nextId = 1; // Для автоинкремента
    private long id; //Значение поля должн обыть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int numberOfParticipants; //Значение поля должно быть больше 0
    private int albumsCount; //Значение поля должно быть больше 0
    private MusicGenre genre; //Поле не может быть null
    private Person frontMan; //Поле может быть null

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
}