package by.han.arrays.comparator;

import by.han.arrays.domain.IntArrayEntity;

import java.util.Comparator;

public class ByFirstElementComparator implements Comparator<IntArrayEntity> {
    @Override
    public int compare(IntArrayEntity a, IntArrayEntity b) {
        int[] aValues = a.getValues();
        int[] bValues = b.getValues();
        boolean emptyA = aValues.length == 0;
        boolean emptyB = bValues.length == 0;
        if (emptyA && emptyB) return 0;
        if (emptyA) return 1;
        if (emptyB) return -1;
        return Integer.compare(aValues[0], bValues[0]);
    }
}
