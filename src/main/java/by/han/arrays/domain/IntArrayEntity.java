package by.han.arrays.domain;

import java.util.Arrays;

public final class IntArrayEntity {
    private final int[] values;

    IntArrayEntity(int[] values) {
        if (values == null) {
            throw new IllegalArgumentException("Array must not be null");
        }
        this.values = Arrays.copyOf(values, values.length);
    }

    public int size() {
        return values.length;
    }

    public int[] getValues() {
        return Arrays.copyOf(values, values.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntArrayEntity that = (IntArrayEntity) o;
        return Arrays.equals(this.values, that.values);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(values);
    }

    @Override
    public String toString() {
        return "IntArrayEntity{" +
                "values=" + Arrays.toString(values) +
                '}';
    }
}
