package by.han.arrays.service.impl;

import by.han.arrays.domain.IntArrayEntity;
import by.han.arrays.service.SortService;

import java.util.Objects;

public final class ArrayInsertSortService implements SortService {

    @Override
    public int[] sort(IntArrayEntity array) {
        Objects.requireNonNull(array, "Array must not be null");

        int[] result = array.getValues();
        for (int index = 1; index < result.length; index++) {
            int current = result[index];
            int position = index - 1;
            while (position >= 0 && result[position] > current) {
                result[position + 1] = result[position];
                position--;
            }
            result[position + 1] = current;
        }
        return result;
    }
}
