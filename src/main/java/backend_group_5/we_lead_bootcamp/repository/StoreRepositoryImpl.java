package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.StoreCategory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

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
    public List<Store> findStoresByCategory(StoreCategory category) {
        //ok auto isws thelei allagi ston tropo pou filtrarei
        return storage.values()
                .stream()
                .filter(c ->category.equals(c.getCategory()))
                .collect(Collectors.toList());
    }
    @Override
    public Store findByCategory(StoreCategory category) {
        return storage.values()
                .stream()
                .filter(c -> category.equals(c.getCategory()))
                .findFirst()
                .orElse(null);

    }


    /*@Override edw evgaze provlima diarkws (mporei na epireastike to master gt to 1o change katalathos pige ekei uwu
    sto Base logw tou exists method me Long h T item - )
    public boolean exists(Long item) {
        return false;
    }*/
}