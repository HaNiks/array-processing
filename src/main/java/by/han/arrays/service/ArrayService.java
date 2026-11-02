
package by.han.arrays.service;

import by.han.arrays.domain.entity.IntArrayEntity;
import by.han.arrays.specification.enums.ComparisonArrayType;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface ArrayService {

    IntArrayEntity create(int[] values);

    void add(IntArrayEntity entity);

    boolean deleteById(long id);

    int deleteAll();

    Optional<IntArrayEntity> findById(long id);

    List<IntArrayEntity> findAll();

    List<IntArrayEntity> findBySum(ComparisonArrayType comparison, double threshold);

    List<IntArrayEntity> findByAverage(ComparisonArrayType comparison, double threshold);

    List<IntArrayEntity> findByMax(ComparisonArrayType comparison, double threshold);

    List<IntArrayEntity> findByMin(ComparisonArrayType comparison, double threshold);

    List<IntArrayEntity> findByCount(ComparisonArrayType comparison, double threshold);

    List<IntArrayEntity> findByName(String name);

    List<IntArrayEntity> sort(Comparator<IntArrayEntity> comparator);

    List<IntArrayEntity> sortById();

    List<IntArrayEntity> sortByName();

    List<IntArrayEntity> sortByFirstElement();

    List<IntArrayEntity> sortByCount();
}
