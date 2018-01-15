package app.dao;

import java.util.List;

public interface NativeSqlDAO <T> {

    void createUsingNativeSql(T entity);

    List<T> readAllUsingNativeSql();

    T readByIdUsingNativeSql(short id);

    void updateUsingNativeSql(T updatedEntity, short id);

    void deleteUsingNativeSql(short id);
}
