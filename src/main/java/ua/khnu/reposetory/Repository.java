package ua.khnu.reposetory;

import ua.khnu.db.transaction.Transaction;

public interface Repository<T> {
    @Transaction
    T add(T entity);
    T query(String query);
    boolean delete(T entity);
    boolean update(T entity);
}
