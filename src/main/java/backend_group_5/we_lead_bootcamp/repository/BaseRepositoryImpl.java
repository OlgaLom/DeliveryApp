package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.base.BaseComponent;
import backend_group_5.we_lead_bootcamp.model.BaseModel;
import backend_group_5.we_lead_bootcamp.service.BaseService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public abstract class BaseRepositoryImpl<T extends BaseModel> extends BaseComponent implements BaseService<T,Long> {
//    Get storage for luck :)
    protected abstract ConcurrentHashMap<Long,T> getStorage();
//    Use  AtomicLong in order to get an auto-increment number for the items that we will create.
    protected abstract AtomicLong getSequence();

    @Override
    public T create(final T item) {
        item.setId(getSequence().incrementAndGet());
        getStorage().put(item.getId(),item);
        return item;
    }

    @Override
    public List<T> createAll(final List<T> items) {
//        In the main code the argument "createdItems" is labeled as "updateItems" - Check for more info
        List<T> createdItems = new ArrayList<>();

        items.forEach(i->{
            i.setId(getSequence().incrementAndGet());
            getStorage().put(i.getId(),i);
            createdItems.add(i);
        });
        return createdItems;
    }

    @Override
    public List<T> createAll(final T... items) {
        return createAll(Arrays.asList(items));
    }

    @Override
    public void update(final T item) {
        getStorage().put(item.getId(), item);
    }

    @Override
    public void delete(final T item){
        deleteById(item.getId());
    }

    @Override
    public void deleteById(Long id) {
        getStorage().remove(id);
    }

    @Override
    public T getById(final Long id){ return getStorage().get(id);}

    @Override
    public boolean exists(final T item) {
        return getStorage().containsKey(item.getId());
    }

    //    Check more info about storage data-structure/library
    @Override
    public List<T> findAll(){return getStorage().values().stream().toList();}

    @Override
    public Long count() {
        return (long) getStorage().size();
    }
}
