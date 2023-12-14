package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.BaseModel;
import com.fasterxml.jackson.databind.ser.Serializers;

import java.util.List;

public interface BaseService<T extends BaseModel, K> {
    T create(T item);
    List<T> createAll(List<T> items);

    List<T> CreateAll(T... items);

    void update(T item);

    void delete(T item);

    void deleteById(K id);

    T get(K item);

    boolean exists(T item);

    List<T> findAll();

    Long count();



}
