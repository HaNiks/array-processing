package by.han.arrays.application;

import by.han.arrays.domain.ArrayFactory;
import by.han.arrays.domain.IntArrayEntity;
import by.han.arrays.exception.InvalidArrayException;
import by.han.arrays.parser.IntArrayParser;
import by.han.arrays.reader.ArrayFileReader;
import by.han.arrays.service.StatisticService;
import by.han.arrays.service.impl.ArrayInsertSortService;
import by.han.arrays.service.impl.ArrayQuickSortService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

public final class ArrayApplication {
    private static final Logger LOGGER = LogManager.getLogger(ArrayApplication.class);

    private final ArrayFileReader fileReader;
    private final IntArrayParser parser;
    private final ArrayFactory factory;
    private final StatisticService statisticService;
    private final ArrayQuickSortService quickSortService;
    private final ArrayInsertSortService insertionSortService;

    public ArrayApplication(
            ArrayFileReader fileReader, IntArrayParser parser,
            ArrayFactory factory,
            StatisticService statisticService,
            ArrayQuickSortService quickSortService,
            ArrayInsertSortService insertionSortService) {
        this.fileReader = fileReader;
        this.parser = parser;
        this.factory = factory;
        this.statisticService = statisticService;
        this.quickSortService = quickSortService;
        this.insertionSortService = insertionSortService;
    }

    public void run(Path inputPath) throws IOException {
        for (String line : fileReader.read(inputPath)) {
            processLine(line);
        }
    }

    private void processLine(String line) {
        try {
            int[] rawNumbers = parser.parse(line);

            IntArrayEntity array = factory.create(rawNumbers);
            logResults(array);

        } catch (InvalidArrayException exception) {
            LOGGER.error("Could not process input line: '{}'. Reason: {}", line, exception.getMessage());
        } catch (ArithmeticException exception) {
            LOGGER.error("Arithmetic error for input line: '{}'", line, exception);
        }
    }


    private void logResults(IntArrayEntity array) {
        LOGGER.info("Input array: {}", Arrays.toString(array.getValues()));
        LOGGER.info("Minimum: {}", statisticService.findMin(array).orElseThrow());
        LOGGER.info("Maximum: {}", statisticService.findMax(array).orElseThrow());
        LOGGER.info("Sum: {}", statisticService.calculateSum(array).orElseThrow());
        LOGGER.info("Average: {}", statisticService.calculateAverage(array).orElseThrow());
        LOGGER.info("Insertion sort: {}", Arrays.toString(insertionSortService.sort(array)));
        LOGGER.info("Quick sort: {}", Arrays.toString(quickSortService.sort(array)));
    }
}