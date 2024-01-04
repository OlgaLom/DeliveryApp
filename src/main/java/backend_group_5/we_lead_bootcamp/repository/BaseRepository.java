package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.BaseModel;

import java.util.List;

public interface BaseRepository<T extends BaseModel, K> {
//    T for Type | K for Key
    T create(T item);
    List<T> createAll(List<T> items);

    List<T> createAll(T... items);

    void update(T item);
    void delete(T item);
    void deleteById(K id);

    T getById(K id);

    boolean exists(Long item);

    List<T> findAll();

    Long count();


}
