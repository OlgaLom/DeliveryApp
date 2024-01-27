package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.BaseModel;


import java.util.List;

public interface BaseService<T extends BaseModel, K> {
//    T stands for Type | K stands for Key
    T create(T item);
    List<T> createAll(List<T> items);

    List<T> createAll(T... items);

    void update(T item);

    void delete(T item);

    void deleteById(K id);

    T getById(K item);

    boolean exists(T item);

    List<T> findAll();

    Long count();



}
