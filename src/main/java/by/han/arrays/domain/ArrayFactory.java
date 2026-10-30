package by.han.arrays.domain;

import by.han.arrays.exception.InvalidArrayException;

public interface ArrayFactory {
    IntArrayEntity create(int[] values) throws InvalidArrayException;
}
