package by.han.arrays.comparator;

import by.han.arrays.domain.entity.IntArrayEntity;

import java.util.Comparator;

public class ByNameComparator implements Comparator<IntArrayEntity> {
    @Override
    public int compare(IntArrayEntity a, IntArrayEntity b) {
        return a.getName().compareToIgnoreCase(b.getName());
    }
}
