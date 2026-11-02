package by.han.arrays.domain.warehouse;

import by.han.arrays.domain.entity.IntArrayEntity;

import java.util.HashMap;
import java.util.Map;

public final class Warehouse {
    private static Warehouse instance;
    private final Map<Long, ArrayStatistics> storage = new HashMap<>();

    private Warehouse() {
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public void recompute(IntArrayEntity entity) {
        storage.put(entity.getId(), ArrayStatistics.of(entity));
    }

    public ArrayStatistics get(long id) {
        return storage.get(id);
    }

    public void remove(long id) {
        storage.remove(id);
    }

    public void clear() {
        storage.clear();
    }
}