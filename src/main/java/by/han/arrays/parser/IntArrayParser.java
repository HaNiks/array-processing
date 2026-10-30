package by.han.arrays.parser;

import by.han.arrays.exception.InvalidArrayException;

public interface IntArrayParser {
    int[] parse(String line) throws InvalidArrayException;
}