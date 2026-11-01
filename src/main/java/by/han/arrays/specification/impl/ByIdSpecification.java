package by.han.arrays.specification.impl;

import by.han.arrays.domain.IntArrayEntity;
import by.han.arrays.specification.Specification;

public class ByIdSpecification implements Specification<IntArrayEntity> {
    private final long id;

    public ByIdSpecification(long id) {
        this.id = id;
    }

    @Override
    public boolean isSatisfiedBy(IntArrayEntity item) {
        return item.getId() == id;
    }
}