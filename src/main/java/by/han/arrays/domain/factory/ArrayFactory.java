package by.han.arrays.domain.factory;

import by.han.arrays.domain.entity.IntArrayEntity;
import by.han.arrays.exception.InvalidArrayException;

public interface ArrayFactory {
    IntArrayEntity create(int[] values) throws InvalidArrayException;
}
