package by.han.arrays.reader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface ArrayFileReader {
    List<String> read(Path path) throws IOException;
}
