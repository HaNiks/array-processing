package by.han.arrays.comparator;

import by.han.arrays.domain.IntArrayEntity;

import java.util.Comparator;

public class ByIdComparator implements Comparator<IntArrayEntity> {
    @Override
    public int compare(IntArrayEntity a, IntArrayEntity b) {
        return Long.compare(a.getId(), b.getId());
    }
}