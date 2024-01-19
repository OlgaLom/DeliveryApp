package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.Category;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CategoryRepositoryImpl extends BaseRepositoryImpl<Category> implements CategoryRepository{
    private final ConcurrentHashMap<Long,Category> storage=new ConcurrentHashMap<>();
    private final AtomicLong sequence =new AtomicLong(0);

    @Override
    protected ConcurrentHashMap<Long,Category>getStorage(){
        return storage;
    }
    @Override
    protected AtomicLong getSequence(){
        return sequence;
    }

    @Override
    public Category findByDescription(final String description){
        return storage.values()
                .stream()
                .filter(c->description.equalsIgnoreCase(c.getDescription()))
                .findFirst()
                .orElse(null);
    }
}
