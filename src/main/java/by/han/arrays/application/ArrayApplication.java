package by.han.arrays.application;

import by.han.arrays.comparator.ByFirstElementComparator;
import by.han.arrays.domain.entity.IntArrayEntity;
import by.han.arrays.domain.warehouse.ArrayStatistics;
import by.han.arrays.domain.warehouse.Warehouse;
import by.han.arrays.exception.InvalidArrayException;
import by.han.arrays.parser.IntArrayParser;
import by.han.arrays.reader.ArrayFileReader;
import by.han.arrays.service.ArrayService;
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
    private final ArrayQuickSortService quickSortService;
    private final ArrayInsertSortService insertionSortService;
    private final ArrayService arrayService;
    private final Warehouse warehouse = Warehouse.getInstance();

    public ArrayApplication(
            ArrayFileReader fileReader, IntArrayParser parser,
            ArrayQuickSortService quickSortService,
            ArrayInsertSortService insertionSortService, ArrayService arrayService) {
        this.fileReader = fileReader;
        this.parser = parser;
        this.quickSortService = quickSortService;
        this.insertionSortService = insertionSortService;
        this.arrayService = arrayService;
    }

    public void run(Path inputPath) throws IOException {
        for (String line : fileReader.read(inputPath)) {
            processLine(line);
        }

        logResults(arrayService);

        testObserver();
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
        ArrayStatistics stats = warehouse.get(array.getId());
        LOGGER.info("Input array: {}", Arrays.toString(array.getValues()));
        LOGGER.info("Minimum: {}", stats.min());
        LOGGER.info("Maximum: {}", stats.max());
        LOGGER.info("Sum: {}", stats.sum());
        LOGGER.info("Average: {}", stats.avg());
        LOGGER.info("Insertion sort: {}", Arrays.toString(insertionSortService.sort(array)));
        LOGGER.info("Quick sort: {}", Arrays.toString(quickSortService.sort(array)));
    }

    private void logResults(ArrayService service) {
        LOGGER.info("All entities: {}", service.findAll());
        LOGGER.info("Find by id=0: {}", service.findById(0L).orElse(null));
        LOGGER.info("Find by name 'Array #0': {}", service.findByName("Array #0"));
        LOGGER.info("Sum > 10: {}", service.findBySum(ComparisonArrayType.GREATER, 10));
        LOGGER.info("Sum < 10: {}", service.findBySum(ComparisonArrayType.LESS, 10));
        LOGGER.info("Sum == 6: {}", service.findBySum(ComparisonArrayType.EQUAL, 6));
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

    private void testObserver() {
        Warehouse warehouse = Warehouse.getInstance();

        arrayService.findById(0L).ifPresentOrElse(entity -> {
            LOGGER.info("1. Stats BEFORE change: {}", warehouse.get(entity.getId()));

            LOGGER.info("2. Changing element [0] to 999...");
            entity.setValue(0, 999);

            LOGGER.info("3. Stats AFTER change:  {}", warehouse.get(entity.getId()));

            LOGGER.info("4. Find by sum > 1000: {}", arrayService.findBySum(ComparisonArrayType.GREATER, 1000));
        }, () -> LOGGER.warn("Entity with id=0 not found"));
    }
}