package ru.askar.lab5.object;

import java.util.Objects;

public class Event implements Comparable<Event> {
    private static Integer nextId = 1; // Для автоинкремента
    private final Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String description; //Длина строки не должна быть больше 1573, Поле не может быть null
    private EventType eventType; //Поле может быть null

    public Event(String name, String description, EventType eventType) {
        this.id = nextId;
        nextId++;
        setName(name);
        setDescription(description);
        setEventType(eventType);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) && Objects.equals(name, event.name) && Objects.equals(description, event.description) && eventType == event.eventType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, eventType);
    }

    @Override
    public int compareTo(Event o) {
        return Integer.compare(this.id, o.id);
    }

    @Override
    public String toString() {
        return "Событие" +
                ": id=" + id +
                ", название='" + name + "'" +
                ", описание='" + description + "'" +
                ", тип=" + eventType + ";";
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Название не может быть null или пустым");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.length() > 1573) {
            throw new IllegalArgumentException("Описание не может быть null");
        }
        this.description = description;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}