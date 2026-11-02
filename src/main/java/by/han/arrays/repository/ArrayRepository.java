package by.han.arrays.repository;

import by.han.arrays.domain.entity.IntArrayEntity;
import by.han.arrays.specification.Specification;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface ArrayRepository {
    void add(IntArrayEntity entity);

    int deleteAll();

    boolean deleteById(long id);

    Optional<IntArrayEntity> findById(long id);

    List<IntArrayEntity> findAll();

    List<IntArrayEntity> findBySpecification(Specification<IntArrayEntity> spec);

    List<IntArrayEntity> sort(Comparator<IntArrayEntity> comparator);
}
