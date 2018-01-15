package app.dao;

import java.util.List;

public interface DAO <T> {

    void create(T entity);

    List<T> readAll();

    T readById(short id);

    void update(T updatedEntity, short id);

    void delete(short id);

}
