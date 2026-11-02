package by.han.arrays.domain.factory;

import by.han.arrays.domain.entity.IntArrayEntity;
import by.han.arrays.exception.InvalidArrayException;

public final class IntArrayFactory implements ArrayFactory {
    @Override
    public IntArrayEntity create(int[] values) throws InvalidArrayException {
        if (values == null || values.length == 0) {
            throw new InvalidArrayException("Source array must not be null or empty");
        }
        return new IntArrayEntity(values);
    }
}