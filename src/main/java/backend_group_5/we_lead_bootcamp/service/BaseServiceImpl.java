package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.base.BaseComponent;
import backend_group_5.we_lead_bootcamp.model.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public abstract class BaseServiceImpl<T extends BaseModel> extends BaseComponent implements BaseService<T,Long>{
//    T stands for Type
    protected abstract JpaRepository<T, Long> getRepository();

    @Override
    public T create(final T item){ return getRepository().save(item);}

    @Override
    public List<T> createAll(final List<T> items){return getRepository().saveAll(items);}

    @SafeVarargs
    @Override
    public final List<T> createAll(final T... items) {
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
    @Transactional(readOnly = true)
    public T getById(final Long id) {
//        T  item = getRepository().findById(id).orElseThrow();
//        return item;
        return getRepository().findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(final T item){return getRepository().existsById(item.getId());}

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() { return getRepository().findAll(); }

    @Override
    @Transactional(readOnly = true)
    public Long count() { return getRepository().count();  }

}
