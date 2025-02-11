package ru.askar.lab5.object;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.askar.lab5.cli.input.InputReader;
import ru.askar.lab5.cli.output.OutputWriter;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket implements Comparable<Ticket> {
    /**
     * Хранение следующего id для автоинкремента при создании объектов
     */
    private static Long nextId = 1L;
    private final java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private long price; //Значение поля должно быть больше 0
    private TicketType type; //Поле не может быть null
    private Event event; //Поле может быть null

    /**
     * Создание экземпляра с заданным id, если такого ещё не было
     *
     * @throws IllegalArgumentException - если данный id невозможен
     */
    @JsonCreator
    public Ticket(@JsonProperty("id") Long id, @JsonProperty("name") String name, @JsonProperty("coordinates") Coordinates coordinates, @JsonProperty("price") long price, @JsonProperty("type") TicketType type, @JsonProperty("event") Event event) {
//        if (id < nextId) throw new IllegalArgumentException("Билет с таким id уже был");
        setId(id);
        setName(name);
        setCoordinates(coordinates);
        this.creationDate = LocalDateTime.now();
        setPrice(price);
        setType(type);
        setEvent(event);
    }

    /**
     * Создание экземпляра с автоинкрементным id.
     */
    public Ticket(String name, Coordinates coordinates, long price, TicketType type, Event event) {
        this(nextId++, name, coordinates, price, type, event);
    }

    /**
     * Создание экземпляра с пользовательским вводом.
     * Параметры помимо <code>name</code> и <code>price</code> будут считываться из заданного метода
     *
     * @param outputWriter - способ печати ответа
     * @param inputReader  - способ считывания входных данных
     * @param id           - желаемый id билета. <code>null</code>, если автоинкремент
     * @param name         - название
     * @param price        - цена
     * @return - созданный Ticket
     */
    public static Ticket createTicket(OutputWriter outputWriter, InputReader inputReader, Long id, String name, long price) {
        Coordinates coordinates = Coordinates.createCoordinates(outputWriter, inputReader);
        TicketType ticketType = TicketType.createTicketType(outputWriter, inputReader);
        outputWriter.writeOnSuccess("Хотите ввести событие? (y/n): ");
        if (!inputReader.getInputString().equals("y")) {
            if (id == null) {
                return new Ticket(name, coordinates, price, ticketType, null);
            }
            return new Ticket(id, name, coordinates, price, ticketType, null);
        }
        Event event = Event.createEvent(outputWriter, inputReader);
        if (id == null) {
            return new Ticket(name, coordinates, price, ticketType, event);
        }
        return new Ticket(id, name, coordinates, price, ticketType, event);
    }

    public static Long getNextId() {
        return nextId;
    }

    public static void setNextId(Long newNextId) {
        if (newNextId <= 0) {
            throw new IllegalArgumentException("ID должен быть больше 0");
        }
        if (newNextId < nextId) {
            throw new IllegalArgumentException("nextID не может стать меньше");
        }
        nextId = newNextId;
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

    /**
     * Сравнение, реализованное через разницу id'шников
     */
    @Override
    public int compareTo(Ticket o) {
        return Long.compare(this.id, o.id);
        //TODO: поменять
    }

    @Override
    public String toString() {
        return "Билет" + ": id=" + id + ", название='" + name + "'" + ", координаты=" + coordinates + ", дата создания=" + creationDate + ", цена=" + price + ", тип=" + type + ", событие=" + event + ";";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID не может быть null");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("ID должен быть больше 0");
        }
        this.id = id;
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

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Координаты не могут быть null");
        }
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше 0");
        }
        this.price = price;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        if (type == null) {
            throw new IllegalArgumentException("Тип билета не может быть null");
        }
        this.type = type;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
