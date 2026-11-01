package by.han.arrays.application;

import by.han.arrays.comparator.ByFirstElementComparator;
import by.han.arrays.domain.IntArrayEntity;
import by.han.arrays.exception.InvalidArrayException;
import by.han.arrays.parser.IntArrayParser;
import by.han.arrays.reader.ArrayFileReader;
import by.han.arrays.service.ArrayService;
import by.han.arrays.service.StatisticService;
import by.han.arrays.service.impl.ArrayInsertSortService;
import by.han.arrays.service.impl.ArrayQuickSortService;
import by.han.arrays.specification.enums.ComparisonArrayType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

public final class ArrayApplication {
    private static final Logger LOGGER = LogManager.getLogger(ArrayApplication.class);

    private final ArrayFileReader fileReader;
    private final IntArrayParser parser;
    private final StatisticService statisticService;
    private final ArrayQuickSortService quickSortService;
    private final ArrayInsertSortService insertionSortService;
    private final ArrayService arrayService;

    public ArrayApplication(
            ArrayFileReader fileReader, IntArrayParser parser,
            StatisticService statisticService,
            ArrayQuickSortService quickSortService,
            ArrayInsertSortService insertionSortService, ArrayService arrayService) {
        this.fileReader = fileReader;
        this.parser = parser;
        this.statisticService = statisticService;
        this.quickSortService = quickSortService;
        this.insertionSortService = insertionSortService;
        this.arrayService = arrayService;
    }

    public void run(Path inputPath) throws IOException {
        for (String line : fileReader.read(inputPath)) {
            processLine(line);
        }

        logResults(arrayService);
    }

    private void processLine(String line) {
        try {
            int[] rawNumbers = parser.parse(line);

            IntArrayEntity array = arrayService.create(rawNumbers);
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

    private void logResults(ArrayService service) {
        LOGGER.info("All entities: {}", service.findAll());
        LOGGER.info("Find by id=0: {}", service.findById(0L).orElse(null));
        LOGGER.info("Find by name 'Array #0': {}", service.findByName("Array #0"));
        LOGGER.info("Sum > 10: {}", service.findBySum(ComparisonArrayType.GREATER, 10));
        LOGGER.info("Sum < 10: {}", service.findBySum(ComparisonArrayType.LESS, 10));
        LOGGER.info("Sum == 10: {}", service.findBySum(ComparisonArrayType.EQUAL, 6));
        LOGGER.info("Average > 1: {}", service.findByAverage(ComparisonArrayType.GREATER, 1));
        LOGGER.info("Max > 5: {}", service.findByMax(ComparisonArrayType.GREATER, 40));
        LOGGER.info("Min < 3: {}", service.findByMin(ComparisonArrayType.LESS, 3));
        LOGGER.info("Count > 2: {}", service.findByCount(ComparisonArrayType.GREATER, 4));
        LOGGER.info("Sorted by id: {}", service.sortById());
        LOGGER.info("Sort by first element comparator: {}", service.sort(new ByFirstElementComparator()));
        LOGGER.info("Sorted by name: {}", service.sortByName());
        LOGGER.info("Sorted by first element: {}", service.sortByFirstElement());
        LOGGER.info("Sorted by count: {}", service.sortByCount());
        LOGGER.info("Deleted id=1: {}", service.deleteById(1L));
        LOGGER.info("All after delete: {}", service.findAll());
        LOGGER.info("Deleted all: {}", service.deleteAll());
    }
}