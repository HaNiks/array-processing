package by.han.arrays.service.impl;

import by.han.arrays.domain.IntArrayEntity;
import by.han.arrays.service.StatisticService;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public final class ArrayStatisticService implements StatisticService {

    @Override
    public OptionalDouble calculateAverage(IntArrayEntity array) {
        if (array == null) {
            return OptionalDouble.empty();
        }
        return Arrays.stream(array.getValues()).average();
    }

    @Override
    public OptionalInt findMin(IntArrayEntity array) {
        if (array == null) {
            return OptionalInt.empty();
        }
        return Arrays.stream(array.getValues()).min();
    }

    @Override
    public OptionalInt findMax(IntArrayEntity array) {
        if (array == null) {
            return OptionalInt.empty();
        }
        return Arrays.stream(array.getValues()).max();
    }

    @Override
    public OptionalLong calculateSum(IntArrayEntity array) {
        if (array == null) {
            return OptionalLong.empty();
        }
        try {
            long sum = Arrays.stream(array.getValues())
                    .asLongStream()
                    .reduce(0L, Math::addExact);
            return OptionalLong.of(sum);
        } catch (ArithmeticException exception) {
            return OptionalLong.empty();
        }
    }
}
