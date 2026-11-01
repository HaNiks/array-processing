package by.han.arrays.util;

import by.han.arrays.domain.IntArrayEntity;

public final class ArrayMath {

    private ArrayMath() {
    }

    public static long sum(IntArrayEntity e) {
        long s = 0;
        for (int i = 0; i < e.size(); i++) s += e.getValue(i);
        return s;
    }

    public static double average(IntArrayEntity e) {
        return e.size() == 0 ? 0.0 : (double) sum(e) / e.size();
    }

    public static int max(IntArrayEntity e) {
        if (e.size() == 0) throw new IllegalStateException("empty array");
        int m = e.getValue(0);
        for (int i = 1; i < e.size(); i++) m = Math.max(m, e.getValue(i));
        return m;
    }

    public static int min(IntArrayEntity e) {
        if (e.size() == 0) throw new IllegalStateException("empty array");
        int m = e.getValue(0);
        for (int i = 1; i < e.size(); i++) m = Math.min(m, e.getValue(i));
        return m;
    }

    public static int count(IntArrayEntity e) {
        return e.size();
    }
}