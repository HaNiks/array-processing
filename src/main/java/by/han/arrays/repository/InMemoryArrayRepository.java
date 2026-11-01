package by.han.arrays.repository;

import by.han.arrays.domain.IntArrayEntity;
import by.han.arrays.exception.MemoryArrayException;
import by.han.arrays.specification.Specification;

import java.util.*;

public final class InMemoryArrayRepository implements ArrayRepository {

    private final Map<Long, IntArrayEntity> storage = new HashMap<>();

    @Override
    public void add(IntArrayEntity entity) {
        Objects.requireNonNull(entity, "entity must not be null");
        if (storage.putIfAbsent(entity.getId(), entity) != null) {
            throw new MemoryArrayException("Array with id=" + entity.getId() + " already exists");
        }
    }

    @Override
    public int deleteAll() {
        int countOfDeleted = storage.size();
        storage.clear();
        return countOfDeleted;
    }

    @Override
    public boolean deleteById(long id) {
        return storage.remove(id) != null;
    }

    @Override
    public Optional<IntArrayEntity> findById(long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<IntArrayEntity> findAll() {
        return List.copyOf(storage.values());
    }

    @Override
    public List<IntArrayEntity> findBySpecification(Specification<IntArrayEntity> spec) {
        return storage.values()
                .stream()
                .filter(spec::isSatisfiedBy)
                .toList();
    }

    @Override
    public List<IntArrayEntity> sort(Comparator<IntArrayEntity> comparator) {
        Objects.requireNonNull(comparator, "comparator must not be null");
        List<IntArrayEntity> copy = new ArrayList<>(storage.values());
        copy.sort(comparator);
        return copy;
    }
}
