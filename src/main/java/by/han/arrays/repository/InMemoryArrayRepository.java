package by.han.arrays.repository;

import by.han.arrays.domain.entity.IntArrayEntity;
import by.han.arrays.domain.warehouse.Warehouse;
import by.han.arrays.exception.MemoryArrayException;
import by.han.arrays.observer.Observer;
import by.han.arrays.specification.Specification;

import java.util.*;

public final class InMemoryArrayRepository implements ArrayRepository, Observer {

    private static InMemoryArrayRepository instance;
    private final Map<Long, IntArrayEntity> storage = new HashMap<>();
    private final Warehouse warehouse = Warehouse.getInstance();


    private InMemoryArrayRepository() {
    }

    public static InMemoryArrayRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryArrayRepository();
        }
        return instance;
    }

    @Override
    public void add(IntArrayEntity entity) {
        Objects.requireNonNull(entity, "entity must not be null");
        if (storage.putIfAbsent(entity.getId(), entity) != null) {
            throw new MemoryArrayException("Array with id=" + entity.getId() + " already exists");
        }
        entity.attach(this);
        warehouse.recompute(entity);
    }

    @Override
    public int deleteAll() {
        int countOfDeleted = storage.size();
        for (IntArrayEntity entity : storage.values()) {
            entity.detach(this);
        }
        warehouse.clear();
        storage.clear();
        return countOfDeleted;
    }

    @Override
    public boolean deleteById(long id) {
        IntArrayEntity removed = storage.remove(id);
        if (removed != null) {
            removed.detach(this);
            warehouse.remove(id);
            return true;
        }
        return false;
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

    @Override
    public void update(IntArrayEntity entity) {
        warehouse.recompute(entity);
    }
}
