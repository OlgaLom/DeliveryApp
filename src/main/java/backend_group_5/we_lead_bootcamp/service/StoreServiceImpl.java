package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.StoreCategory;
import backend_group_5.we_lead_bootcamp.repository.BaseRepository;
import backend_group_5.we_lead_bootcamp.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl extends BaseServiceImpl<Store> implements StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Store createStore(Store store) {
        return storeRepository.create(store);
    }

    @Override
    public List<Store> createAllStores(List<Store> stores) {
        return storeRepository.createAll(stores);
    }

    @Override
    public Store getByCategory(StoreCategory category) {
        return storeRepository.findByCategory(category);
    }

    @Override
    public List<Store> getStoresByCategory(StoreCategory category) {
        return storeRepository.findStoresByCategory(category);
        //expects to retrieve a list of stores that match the given category
    }

    @Override
    public Store updateStoreCategory(Long storeId, StoreCategory newCategory) {
        Store existingStore = getById(storeId);
        if (existingStore != null) {
            existingStore.setCategory(newCategory);
            return storeRepository.create(existingStore);
            //isws prepei na valoume sto baseservice kai ena save method gia na ginetai save sto update oxi create (?)
        }
        return null; //gia handle to not found
    }

    @Override
    public void deleteStoreCategory(Long storeId) {

    }

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
        return storeRepository.exists(storeId);
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
    public List<Store> createAll(Store... items) {
        return null;
    }

    @Override
    protected BaseRepository<Store, Long> getRepository() {
        return null;
    }
}


