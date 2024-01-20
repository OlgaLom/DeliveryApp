package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ProductCategoryRepositoryImpl extends BaseRepositoryImpl<ProductCategory> implements ProductCategoryRepository {
    private final ConcurrentHashMap<Long, ProductCategory> storage=new ConcurrentHashMap<>();
    private final AtomicLong sequence =new AtomicLong(0);

    @Override
    protected ConcurrentHashMap<Long, ProductCategory>getStorage(){
        return storage;
    }
    @Override
    protected AtomicLong getSequence(){
        return sequence;
    }

    @Override
    public ProductCategory findByDescription(final String description){
        return storage.values()
                .stream()
                .filter(c->description.equalsIgnoreCase(c.getDescription()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public ProductCategory findByName(ProductCategory name) {
        return (ProductCategory) storage.values()
                .stream()
                .filter(c->name.equals(c.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductCategory> findProductByName(ProductCategory name) {
        return storage.values()
                .stream()
                .filter(c->name.equals(c.getName()))
                .collect(Collectors.toList());
    }
}
