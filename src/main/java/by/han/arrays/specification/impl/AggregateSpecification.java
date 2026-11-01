package by.han.arrays.specification.impl;

import by.han.arrays.domain.IntArrayEntity;
import by.han.arrays.specification.Specification;
import by.han.arrays.specification.enums.AggregateArrayType;
import by.han.arrays.specification.enums.ComparisonArrayType;
import by.han.arrays.util.ArrayMath;

public class AggregateSpecification implements Specification<IntArrayEntity> {

    private final AggregateArrayType aggregate;
    private final ComparisonArrayType comparison;
    private final double threshold;

    public AggregateSpecification(AggregateArrayType aggregate,
                                  ComparisonArrayType comparison,
                                  double threshold) {
        this.aggregate = aggregate;
        this.comparison = comparison;
        this.threshold = threshold;
    }

    @Override
    public boolean isSatisfiedBy(IntArrayEntity item) {
        double value = extract(item);
        return switch (comparison) {
            case GREATER -> value > threshold;
            case LESS -> value < threshold;
            case EQUAL -> value == threshold;
        };
    }

    private double extract(IntArrayEntity e) {
        return switch (aggregate) {
            case SUM -> ArrayMath.sum(e);
            case AVERAGE -> ArrayMath.average(e);
            case MAX -> ArrayMath.max(e);
            case MIN -> ArrayMath.min(e);
            case COUNT -> ArrayMath.count(e);
        };
    }
}