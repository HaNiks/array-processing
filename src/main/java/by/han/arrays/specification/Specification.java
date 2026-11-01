package by.han.arrays.specification;

public interface Specification<T> {
    boolean isSatisfiedBy(T item);
}