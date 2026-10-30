package by.han.arrays.service;

import by.han.arrays.domain.IntArrayEntity;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public interface StatisticService {
    OptionalDouble calculateAverage(IntArrayEntity array);

    OptionalInt findMin(IntArrayEntity array);

    OptionalInt findMax(IntArrayEntity array);

    OptionalLong calculateSum(IntArrayEntity array);
}
