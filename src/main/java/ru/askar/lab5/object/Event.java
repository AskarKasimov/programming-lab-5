package ru.askar.lab5.object;

import java.util.Objects;

public class Event implements Comparable<Event> {
    private static Integer nextId = 1; // ��� ��������������
    private final Integer id; //���� �� ����� ���� null, �������� ���� ������ ���� ������ 0, �������� ����� ���� ������ ���� ����������, �������� ����� ���� ������ �������������� �������������
    private String name; //���� �� ����� ���� null, ������ �� ����� ���� ������
    private String description; //����� ������ �� ������ ���� ������ 1573, ���� �� ����� ���� null
    private EventType eventType; //���� ����� ���� null

    public Event(String name, String description, EventType eventType) {
        this.id = nextId;
        nextId++;
        setName(name);
        setDescription(description);
        setEventType(eventType);
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("��� �� ����� ���� null ��� ������");
        }
        this.name = name;
    }

    public void setDescription(String description) {
        if (description == null || description.length() > 1573) {
            throw new IllegalArgumentException("�������� �� ����� ���� null");
        }
        this.description = description;
    }

    public void setEventType(EventType eventType) {
        if (eventType == null) {
            throw new IllegalArgumentException("��� ������� �� ����� ���� null");
        }
        this.eventType = eventType;
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
        return "�������" +
                ": id=" + id +
                ", ��������='" + name + "'" +
                ", ��������='" + description + "'" +
                ", ���=" + eventType + ";";
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public EventType getEventType() {
        return eventType;
    }
}