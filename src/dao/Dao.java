package dao;

import model.Model;

import java.util.List;
import java.util.UUID;

public interface Dao <T extends Model> {
    T save(T model);
    T update (T model);
    void delete (T model);
    List<T> findAll();
    T findById(UUID id);
}
