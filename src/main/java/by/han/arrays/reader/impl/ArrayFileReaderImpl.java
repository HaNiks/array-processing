package by.han.arrays.reader.impl;

import by.han.arrays.reader.ArrayFileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class ArrayFileReaderImpl implements ArrayFileReader {

    @Override
    public List<String> read(Path path) throws IOException {
        return Files.readAllLines(path);
    }
}
