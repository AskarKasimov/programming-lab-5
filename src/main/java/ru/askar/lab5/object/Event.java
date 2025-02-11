package ru.askar.lab5.object;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.cli.output.OutputWriter;
import ru.askar.lab5.exception.InvalidInputFieldException;

import java.util.Objects;

public class Event implements Comparable<Event> {
    /**
     * Хранение следующего id для автоинкремента при создании объектов
     */
    private static Integer nextId = 1;
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String description; //Длина строки не должна быть больше 1573, Поле не может быть null
    private EventType eventType; //Поле может быть null

    public Event(String name,
                 String description,
                 EventType eventType) throws InvalidInputFieldException {
        setId(nextId++);
        setName(name);
        setDescription(description);
        setEventType(eventType);
    }

    @JsonCreator
    public Event(@JsonProperty("id") Integer id,
                 @JsonProperty("name") String name,
                 @JsonProperty("description") String description,
                 @JsonProperty("eventType") EventType eventType) throws InvalidInputFieldException {
        setId(id);
        setName(name);
        setDescription(description);
        setEventType(eventType);
    }

    /**
     * Создание экземпляра с пользовательским вводом.
     *
     * @param outputWriter - способ печати ответа
     * @param inputReader  - способ считывания входных данных
     */
    public static Event createEvent(OutputWriter outputWriter, InputReader inputReader) throws InvalidInputFieldException {
        Event event = new Event(".", "", null);
        outputWriter.writeOnSuccess("Ввод события");

        outputWriter.writeOnSuccess("Введите название события: ");
        event.setName(inputReader.getInputString());

        outputWriter.writeOnSuccess("Введите описание события: ");
        event.setDescription(inputReader.getInputString());

        outputWriter.writeOnSuccess("Хотите ввести тип события? (y/n): ");
        if (!inputReader.getInputString().equals("y")) {
            return event;
        }
        event.setEventType(EventType.createEventType(outputWriter, inputReader));

        return event;
    }

    public static Integer getNextId() {
        return nextId;
    }

    public static void setNextId(Integer newNextId) throws InvalidInputFieldException {
        if (newNextId <= 0) {
            throw new InvalidInputFieldException("ID события должен быть больше 0");
        }
        if (newNextId < nextId) {
            throw new InvalidInputFieldException("nextID события не может стать меньше");
        }
        nextId = newNextId;
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

    public void setId(Integer id) throws InvalidInputFieldException {
        if (id == null) {
            throw new InvalidInputFieldException("ID события не может быть null");
        }
        if (id <= 0) {
            throw new InvalidInputFieldException("ID события должен быть больше 0");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidInputFieldException {
        if (name == null || name.isEmpty()) {
            throw new InvalidInputFieldException("Название события не может быть null или пустым");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws InvalidInputFieldException {
        if (description == null || description.length() > 1573) {
            throw new InvalidInputFieldException("Описание события не может быть null");
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