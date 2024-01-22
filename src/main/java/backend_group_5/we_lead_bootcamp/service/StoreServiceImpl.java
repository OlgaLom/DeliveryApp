package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.StoreCategory;
import backend_group_5.we_lead_bootcamp.model.StoreCategoryVariation;
import backend_group_5.we_lead_bootcamp.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StoreServiceImpl extends BaseServiceImpl<Store> implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    protected JpaRepository<Store, Long> getRepository() {
        return storeRepository;
    }

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public List<Store> createAllStores(List<Store> stores) {
        return storeRepository.saveAll(stores);
    }

    @Override
    public Store getByCategory(StoreCategory category) {
        return storeRepository.findFirstByCategory(category);
    }

    @Override
    public List<Store> getStoresByCategory(StoreCategory category) {
        return storeRepository.findStoresByCategory(category);
        //expects to retrieve a list of stores that match the given category
    }

    @Override
    public Store updateStoreCategory(Long storeId, StoreCategoryVariation newCategory) {
        Store existingStore = getById(storeId);
        if (existingStore != null) {
            existingStore.setCategory(newCategory);
            return storeRepository.save(existingStore);
        }
        return null; //gia handle to not found
    }

    @Override
    public void deleteStoreCategory(Long storeId) {}

    @Override
    public void deleteStoreById(Long storeId) {
        storeRepository.deleteById(storeId);
    }

    @Override
    public Store getStoreById(Long storeId) {
        return getById(storeId);
    }

    @Override
    public boolean doesStoreExist(Store storeId) {
        return storeRepository.existsById(storeId.getId());
    }

    @Override
    public List<Store> findAllStores() {
        return findAll();
    }

    @Override
    public long countStores() {
        return count();
    }

    @Override
    public BigDecimal calculateTotalOrderAmount(Store store) {
        return null;
    }

    /*ekana return null gia na min varaei, isws mas pei kati allo sto epomeno mathima 22/1
    alternatively to implem htan auto
    @Override
    public BigDecimal calculateTotalOrderAmount(Store store) {
        return store.calculateTotalOrderAmount();
    } */

    @Override
    public List<Store> createAll(Store... items) {
        return null;
    }

}


