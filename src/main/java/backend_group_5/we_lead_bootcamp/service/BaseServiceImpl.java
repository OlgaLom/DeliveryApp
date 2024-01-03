package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.base.BaseComponent;
import backend_group_5.we_lead_bootcamp.model.BaseModel;
import backend_group_5.we_lead_bootcamp.repository.BaseRepository;

import java.util.Arrays;
import java.util.List;

public abstract class BaseServiceImpl<T extends BaseModel> extends BaseComponent implements BaseService<T,Long>{
//    T stands for Type
    protected abstract BaseRepository<T, Long> getRepository();

    @Override
    public T create(final T item){ return getRepository().create(item);}

    @Override
    public List<T> createAll(final List<T> items){return getRepository().createAll(items);}

    @Override
    public List<T> createAll(final T... items) {
        return createAll(Arrays.asList(items));
    }


    @Override
    public void update(final T item){
        getRepository().update(item);
    }
    @Override
    public void delete(final T item){ getRepository().delete(item);}

    @Override
    public void deleteById(final Long id){ getRepository().deleteById(id);}

    @Override
    public T getById(final Long id) { return getRepository().getById(id); }

    @Override
    public boolean exists(final T item){return getRepository().exists(item);}

    @Override
    public List<T> findAll() { return getRepository().findAll(); }

    @Override
    public Long count() { return getRepository().count();  }
}
