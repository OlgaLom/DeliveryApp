package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.base.BaseComponent;
import backend_group_5.we_lead_bootcamp.model.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public abstract class BaseServiceImpl<T extends BaseModel> extends BaseComponent implements BaseService<T,Long>{
//    T stands for Type
    protected abstract JpaRepository<T, Long> getRepository();

    @Override
    public T create(final T item){ return getRepository().save(item);}

    @Override
    public List<T> createAll(final List<T> items){return getRepository().saveAll(items);}

    @Override
    public List<T> createAll(final T... items) {
        return createAll(Arrays.asList(items));
    }

    @Override
    public void update(final T item){
        getRepository().save(item);
    }
    @Override
    public void delete(final T item){ getRepository().delete(item);}

    @Override
    public void deleteById(final Long id){ getRepository().deleteById(id);}

    @Override
    public T getById(final Long id) {
//        T  item = getRepository().findById(id).orElseThrow();
//        return item;
        return getRepository().findById(id).orElseThrow();
    }

    @Override
    public boolean exists(final T item){return getRepository().existsById(item.getId());}

    @Override
    public List<T> findAll() { return getRepository().findAll(); }

    @Override
    public Long count() { return getRepository().count();  }

}
