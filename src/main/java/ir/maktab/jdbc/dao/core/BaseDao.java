package ir.maktab.jdbc.dao.core;

import ir.maktab.jdbc.entity.base.BaseEntity;

import java.util.List;

// CRUD => Create, Read, Update, Delete
public interface BaseDao<T extends BaseEntity<ID>, ID extends Number> {
    void save(T entity);
    void update(ID id, T newEntity);
    void delete(ID id);
    T loadById(ID id);
    List<T> loadAll();

    default void checkUpdate(int rows, UpdateType updateType){
        if (rows == 0)
            System.out.println("Id is Wrong ...");
        else if (updateType.equals(UpdateType.UPDATE))
            System.out.println("successful Updated");
        else if (updateType.equals(UpdateType.DELETE))
            System.out.println("successful Deleted");
        else
            System.out.println("successful saved");
    }
}
