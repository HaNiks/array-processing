package by.han.arrays.service.impl;

import by.han.arrays.comparator.ByCountElementsComparator;
import by.han.arrays.comparator.ByFirstElementComparator;
import by.han.arrays.comparator.ByIdComparator;
import by.han.arrays.comparator.ByNameComparator;
import by.han.arrays.domain.factory.ArrayFactory;
import by.han.arrays.domain.entity.IntArrayEntity;
import by.han.arrays.exception.InvalidArrayException;
import by.han.arrays.repository.ArrayRepository;
import by.han.arrays.repository.InMemoryArrayRepository;
import by.han.arrays.service.ArrayService;
import by.han.arrays.specification.Specification;
import by.han.arrays.specification.enums.AggregateArrayType;
import by.han.arrays.specification.enums.ComparisonArrayType;
import by.han.arrays.specification.impl.AggregateSpecification;
import by.han.arrays.specification.impl.ByNameSpecification;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ArrayServiceImpl implements ArrayService {

    private final ArrayRepository repository;
    private final ArrayFactory factory;

    public ArrayServiceImpl(ArrayFactory factory) {
        this.repository = InMemoryArrayRepository.getInstance();
        this.factory = Objects.requireNonNull(factory, "factory must not be null");
    }


    @Override
    public IntArrayEntity create(int[] values) {
        try {
            IntArrayEntity entity = factory.create(values);
            repository.add(entity);
            return entity;
        } catch (InvalidArrayException e) {
            throw new IllegalArgumentException("Cannot create entity: " + e.getMessage(), e);
        }
    }

    @Override
    public void add(IntArrayEntity entity) {
        repository.add(entity);
    }

    @Override
    public boolean deleteById(long id) {
        return repository.deleteById(id);
    }

    @Override
    public int deleteAll() {
        return repository.deleteAll();
    }


    @Override
    public Optional<IntArrayEntity> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<IntArrayEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public List<IntArrayEntity> findBySum(ComparisonArrayType comparison, double threshold) {
        return findByAggregate(AggregateArrayType.SUM, comparison, threshold);
    }

    @Override
    public List<IntArrayEntity> findByAverage(ComparisonArrayType comparison, double threshold) {
        return findByAggregate(AggregateArrayType.AVERAGE, comparison, threshold);
    }

    @Override
    public List<IntArrayEntity> findByMax(ComparisonArrayType comparison, double threshold) {
        return findByAggregate(AggregateArrayType.MAX, comparison, threshold);
    }

    @Override
    public List<IntArrayEntity> findByMin(ComparisonArrayType comparison, double threshold) {
        return findByAggregate(AggregateArrayType.MIN, comparison, threshold);
    }

    @Override
    public List<IntArrayEntity> findByCount(ComparisonArrayType comparison, double threshold) {
        return findByAggregate(AggregateArrayType.COUNT, comparison, threshold);
    }

    private List<IntArrayEntity> findByAggregate(AggregateArrayType aggregate,
                                                 ComparisonArrayType comparison,
                                                 double threshold) {
        Specification<IntArrayEntity> spec =
                new AggregateSpecification(aggregate, comparison, threshold);
        return repository.findBySpecification(spec);
    }

    @Override
    public List<IntArrayEntity> findByName(String name) {
        return repository.findBySpecification(new ByNameSpecification(name));
    }


    @Override
    public List<IntArrayEntity> sort(Comparator<IntArrayEntity> comparator) {
        return repository.sort(comparator);
    }

    @Override
    public List<IntArrayEntity> sortById() {
        return repository.sort(new ByIdComparator());
    }

    @Override
    public List<IntArrayEntity> sortByName() {
        return repository.sort(new ByNameComparator());
    }

    @Override
    public List<IntArrayEntity> sortByFirstElement() {
        return repository.sort(new ByFirstElementComparator());
    }

    @Override
    public List<IntArrayEntity> sortByCount() {
        return repository.sort(new ByCountElementsComparator());
    }
}