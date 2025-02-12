package ru.askar.lab5.object;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.cli.output.OutputWriter;
import ru.askar.lab5.exception.InvalidInputFieldException;
import ru.askar.lab5.exception.UserRejectedToFillFieldsException;

import java.util.Objects;

public class Event implements Comparable<Event> {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String description; //Длина строки не должна быть больше 1573, Поле не может быть null
    private EventType eventType; //Поле может быть null

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

    private Event() {
    }

    /**
     * Создание экземпляра с пользовательским вводом.
     *
     * @param outputWriter - способ печати ответа
     * @param inputReader  - способ считывания входных данных
     */
    public static Event createEvent(OutputWriter outputWriter, InputReader inputReader, Integer id) throws InvalidInputFieldException, UserRejectedToFillFieldsException {
        Event event = new Event();
        outputWriter.writeOnSuccess("Ввод события");
        event.requestName(outputWriter, inputReader);
        event.requestDescription(outputWriter, inputReader);
        event.requestEventType(outputWriter, inputReader);
        return event;
    }

    private void requestName(OutputWriter outputWriter, InputReader inputReader) throws UserRejectedToFillFieldsException {
        String name;
        do {
            outputWriter.write("Введите название события: ");
            try {
                name = inputReader.getInputString();
                this.setName(name);
            } catch (InvalidInputFieldException e) {
                name = null;
                outputWriter.writeOnFail(e.getMessage());
                outputWriter.writeOnWarning("Хотите попробовать еще раз? (y/n): ");
                String answer = inputReader.getInputString();
                if (answer != null && !answer.equals("y")) {
                    throw new UserRejectedToFillFieldsException();
                }
            }
        } while (name == null);
    }

    private void requestDescription(OutputWriter outputWriter, InputReader inputReader) throws UserRejectedToFillFieldsException {
        String description;
        do {
            outputWriter.write("Введите описание события: ");
            try {
                description = inputReader.getInputString();
                this.setDescription(description);
            } catch (InvalidInputFieldException e) {
                description = null;
                outputWriter.writeOnFail(e.getMessage());
                outputWriter.writeOnWarning("Хотите попробовать еще раз? (y/n): ");
                String answer = inputReader.getInputString();
                if (answer != null && !answer.equals("y")) {
                    throw new UserRejectedToFillFieldsException();
                }
            }
        } while (description == null);
    }

    private void requestEventType(OutputWriter outputWriter, InputReader inputReader) throws UserRejectedToFillFieldsException {
        outputWriter.writeOnWarning("Хотите ввести тип события? (y/n): ");
        String answer = inputReader.getInputString();
        if (answer != null && answer.equals("y")) {
            setEventType(EventType.createEventType(outputWriter, inputReader));
        }
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
        if (name == null) {
            throw new InvalidInputFieldException("Название события не может быть null");
        }
        if (name.isEmpty()) {
            throw new InvalidInputFieldException("Название события не может быть пустым");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws InvalidInputFieldException {
        if (description == null) {
            throw new InvalidInputFieldException("Описание события не может быть null");
        }
        if (description.length() > 1573) {
            throw new InvalidInputFieldException("Длина описания события не должна быть больше 1573");
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