package by.han.arrays.parser.impl;

import by.han.arrays.exception.InvalidArrayException;
import by.han.arrays.parser.IntArrayParser;
import by.han.arrays.validation.ArrayValidator;

import java.util.Arrays;
import java.util.regex.Pattern;

public final class IntArrayParserImpl implements IntArrayParser {

    private static final Pattern TOKEN_PATTERN = Pattern.compile("[,;\\s]+");
    private final ArrayValidator validator;

    public IntArrayParserImpl(ArrayValidator validator) {
        this.validator = validator;
    }

    @Override
    public int[] parse(String line) throws InvalidArrayException {
        if (!validator.isValid(line)) {
            throw new InvalidArrayException(
                    "Input line failed validation (contains invalid characters or empty)");
        }

        String[] tokens = TOKEN_PATTERN.split(line.strip());

        try {
            return Arrays.stream(tokens)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (NumberFormatException exception) {
            throw new InvalidArrayException("Line contains invalid integer tokens", exception);
        }
    }
}
