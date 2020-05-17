package ua.khnu.util;

@FunctionalInterface
public interface ThreeConsumer<T, U, E> {
    void accept(T t, U u, E e) throws IllegalAccessException;
}
