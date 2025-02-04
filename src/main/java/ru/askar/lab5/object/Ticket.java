package ru.askar.lab5.object;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket implements Comparable<Ticket> {
    private static Long nextId = 1L; // ��� ��������������
    private final Long id; //���� �� ����� ���� null, �������� ���� ������ ���� ������ 0, �������� ����� ���� ������ ���� ����������, �������� ����� ���� ������ �������������� �������������
    private String name; //���� �� ����� ���� null, ������ �� ����� ���� ������
    private Coordinates coordinates; //���� �� ����� ���� null
    private final java.time.LocalDateTime creationDate; //���� �� ����� ���� null, �������� ����� ���� ������ �������������� �������������
    private long price; //�������� ���� ������ ���� ������ 0
    private TicketType type; //���� �� ����� ���� null
    private Event event; //���� ����� ���� null

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
            throw new IllegalArgumentException("��� �� ����� ���� null ��� ������");
        }
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("���������� �� ����� ���� null");
        }
        this.coordinates = coordinates;
    }

    public void setPrice(long price) {
        if (price <= 0) {
            throw new IllegalArgumentException("���� ������ ���� ������ 0");
        }
        this.price = price;
    }

    public void setType(TicketType type) {
        if (type == null) {
            throw new IllegalArgumentException("��� ������ �� ����� ���� null");
        }
        this.type = type;
    }

    public void setEvent(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("������� �� ����� ���� null");
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
        return "�����" +
                ": id=" + id +
                ", ��������='" + name + "'" +
                ", ����������=" + coordinates +
                ", ���� ��������=" + creationDate +
                ", ����=" + price +
                ", ���=" + type +
                ", �������=" + event + ";";
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
