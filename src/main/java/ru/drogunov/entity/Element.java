package ru.drogunov.entity;

import java.util.List;
import java.util.Objects;

public class Element {
    private String group;
    private String type;
    private long number;
    private long weight;

    public Element() {
    }
    public Element(List<String> csvRows) {
        int csvColumnIndex = 0;
        this.group = csvRows.get(csvColumnIndex++);
        this.type = csvRows.get(csvColumnIndex++);
        this.number = Long.parseLong(csvRows.get(csvColumnIndex++));
        this.weight = Long.parseLong(csvRows.get(csvColumnIndex++));
        //TODO Переделать создание через метод с ошибкой
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return Objects.equals(group, element.group) && Objects.equals(type, element.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, type);
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Element{" +
                "group='" + group + '\'' +
                ", type='" + type + '\'' +
                ", number=" + number +
                ", weight=" + weight +
                '}';
    }
}
