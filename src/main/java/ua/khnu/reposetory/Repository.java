package ua.khnu.reposetory;


public interface Repository<T> {
    T add(T entity);

    T query(String query);

    boolean delete(T entity);

    boolean update(T entity);
}
