package by.han.arrays.validation.impl;

import by.han.arrays.validation.ArrayValidator;

import java.util.regex.Pattern;

public final class IntArrayValidator implements ArrayValidator {

    private static final Pattern VALID_ARRAY_PATTERN = Pattern.compile("^[0-9,;\\s+-]+$");


    @Override
    public boolean isValid(String line) {
        if (line == null || line.isBlank()) {
            return false;
        }
        return VALID_ARRAY_PATTERN.matcher(line).matches();
    }
}
