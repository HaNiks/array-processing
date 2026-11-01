package by.han.arrays.specification.impl;

import by.han.arrays.domain.IntArrayEntity;
import by.han.arrays.specification.Specification;

public class ByNameSpecification implements Specification<IntArrayEntity> {
    private final String name;

    public ByNameSpecification(String name) {
        this.name = name;
    }

    @Override
    public boolean isSatisfiedBy(IntArrayEntity item) {
        return name.equals(item.getName());
    }
}
