package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.StoreCategory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class StoreRepositoryImpl extends BaseRepositoryImpl<Store> implements StoreRepository {

    private final ConcurrentHashMap<Long, Store> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    public ConcurrentHashMap<Long, Store> getStorage() {
        return storage;
    }

    @Override
    public AtomicLong getSequence() {
        return sequence;
    }

    @Override
    public List<Store> findByCategory(StoreCategory category) {
        //ok auto isws thelei allagi ston tropo pou filtrarei
        return storage.values().stream()
                .filter(store -> store.getCategory().equals(category))
                .toList();
    }

}