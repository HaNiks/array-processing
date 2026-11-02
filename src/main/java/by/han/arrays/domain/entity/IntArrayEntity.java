package by.han.arrays.domain.entity;

import by.han.arrays.observer.Observer;
import by.han.arrays.observer.Subject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class IntArrayEntity implements Subject {
    private final long id;
    private final String name;
    private final int[] values;
    private static long nextId = 0;
    private final List<Observer> observers = new ArrayList<>();

    public IntArrayEntity(int[] values) {
        this.id = nextId++;
        this.name = "Array #" + this.id;
        this.values = Arrays.copyOf(
                Objects.requireNonNull(values, "Values must not be null"),
                values.length);
    }

    public IntArrayEntity(long id, String name, int[] values) {
        this.id = id;
        this.name = Objects.requireNonNull(name, "Name must not be null");
        this.values = Arrays.copyOf(
                Objects.requireNonNull(values, "Values must not be null"),
                values.length);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int size() {
        return values.length;
    }

    public int getValue(int idx) {
        return values[idx];
    }

    public int[] getValues() {
        return Arrays.copyOf(values, values.length);
    }

    public void setValue(int idx, int value) {
        if (idx < 0 || idx >= values.length)
            throw new IllegalArgumentException("Index:" + idx + " not valid");
        this.values[idx] = value;
        notifyObservers();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return id == ((IntArrayEntity) o).id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    @Override
    public String toString() {
        return "IntArrayEntity{id=" + id + ", " +
                "name='" + name + "', " +
                "values=" + Arrays.toString(values) + "}";
    }
}
