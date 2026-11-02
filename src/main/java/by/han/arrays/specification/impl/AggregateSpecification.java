package by.han.arrays.specification.impl;

import by.han.arrays.domain.entity.IntArrayEntity;
import by.han.arrays.domain.warehouse.ArrayStatistics;
import by.han.arrays.domain.warehouse.Warehouse;
import by.han.arrays.specification.Specification;
import by.han.arrays.specification.enums.AggregateArrayType;
import by.han.arrays.specification.enums.ComparisonArrayType;

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
        ArrayStatistics stats = Warehouse.getInstance().get(e.getId());

        if (stats == null) {
            return 0.0;
        }

        return switch (aggregate) {
            case SUM -> stats.sum();
            case AVERAGE -> stats.avg();
            case MAX -> stats.max();
            case MIN -> stats.min();
            case COUNT -> e.size();
        };
    }
}