package dao;

import model.Model;

import java.util.List;

public interface Dao <T extends Model> {
    T save(T model);
    T update (T model);
    void delete (T model);
    List<T> findAll();
    T findById(long id);
}
