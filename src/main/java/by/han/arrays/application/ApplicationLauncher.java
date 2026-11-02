package by.han.arrays.application;

import by.han.arrays.domain.factory.ArrayFactory;
import by.han.arrays.domain.factory.IntArrayFactory;
import by.han.arrays.parser.impl.IntArrayParserImpl;
import by.han.arrays.reader.impl.ArrayFileReaderImpl;
import by.han.arrays.service.impl.ArrayInsertSortService;
import by.han.arrays.service.impl.ArrayQuickSortService;
import by.han.arrays.service.impl.ArrayServiceImpl;
import by.han.arrays.validation.impl.IntArrayValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

public final class ApplicationLauncher {
    private static final Logger LOGGER = LogManager.getLogger(ApplicationLauncher.class);

    public void launch() {
        IntArrayValidator validator = new IntArrayValidator();
        ArrayFactory arrayFactory = new IntArrayFactory();

        ArrayApplication application = new ArrayApplication(
                new ArrayFileReaderImpl(),
                new IntArrayParserImpl(validator),
                new ArrayQuickSortService(),
                new ArrayInsertSortService(),
                new ArrayServiceImpl(arrayFactory));
        try {
            application.run(Path.of("data", "arrays.txt"));
        } catch (IOException exception) {
            LOGGER.error("Failed to read input file", exception);
        }
    }

    public static void main(String[] args) {
        new ApplicationLauncher().launch();
    }
}