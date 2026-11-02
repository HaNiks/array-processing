package by.han.arrays.service.impl;

import by.han.arrays.domain.entity.IntArrayEntity;
import by.han.arrays.service.SortService;

import java.util.Objects;

public final class ArrayQuickSortService implements SortService {

    @Override
    public int[] sort(IntArrayEntity array) {
        Objects.requireNonNull(array, "Array must not be null");

        int[] result = array.getValues();
        quickSort(result, 0, result.length - 1);
        return result;
    }

    private void quickSort(int[] values, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = values[low + (high - low) / 2];
        int left = low;
        int right = high;

        while (left <= right) {
            while (values[left] < pivot) {
                left++;
            }
            while (values[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temporary = values[left];
                values[left] = values[right];
                values[right] = temporary;
                left++;
                right--;
            }
        }

        if (low < right) {
            quickSort(values, low, right);
        }
        if (left < high) {
            quickSort(values, left, high);
        }
    }
}
