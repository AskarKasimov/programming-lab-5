package ru.askar.lab5.object;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket implements Comparable<Ticket> {
    private static Long nextId = 1L; // Для автоинкремента
    private final Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private final java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long price; //Значение поля должно быть больше 0
    private TicketType type; //Поле не может быть null
    private Event event; //Поле может быть null

    public Ticket(String name, Coordinates coordinates, long price, TicketType type, Event event) {
        this.id = nextId;
        nextId++;
        setName(name);
        setCoordinates(coordinates);
        this.creationDate = LocalDateTime.now();
        setPrice(price);
        setType(type);
        setEvent(event);
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть null или пустым");
        }
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Координаты не могут быть null");
        }
        this.coordinates = coordinates;
    }

    public void setPrice(long price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше 0");
        }
        this.price = price;
    }

    public void setType(TicketType type) {
        if (type == null) {
            throw new IllegalArgumentException("Тип билета не может быть null");
        }
        this.type = type;
    }

    public void setEvent(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Событие не может быть null");
        }
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return price == ticket.price && Objects.equals(id, ticket.id) && Objects.equals(name, ticket.name) && Objects.equals(coordinates, ticket.coordinates) && Objects.equals(creationDate, ticket.creationDate) && type == ticket.type && Objects.equals(event, ticket.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, price, type, event);
    }

    @Override
    public int compareTo(Ticket o) {
        return Long.compare(this.id, o.id);
    }

    @Override
    public String toString() {
        return "Билет" +
                ": id=" + id +
                ", название='" + name + "'" +
                ", координаты=" + coordinates +
                ", дата создания=" + creationDate +
                ", цена=" + price +
                ", тип=" + type +
                ", событие=" + event + ";";
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public long getPrice() {
        return price;
    }

    public TicketType getType() {
        return type;
    }

    public Event getEvent() {
        return event;
    }
}
