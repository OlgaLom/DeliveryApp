package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.StoreCategory;

import java.util.List;

public interface StoreService extends BaseService<Store, Long> {

    List<Store> getStoresByCategory(StoreCategory category);
    Store createStore(Store store);

    List<Store> createAllStores(List<Store> stores);

    Store updateStoreCategory(Long storeId, StoreCategory newCategory);

    void deleteStoreCategory(Long storeId);
    void deleteStoreById(Long storeId);
    Store getStoreById(Long storeId);
    boolean doesStoreExist(Long storeId);
    List<Store> findAllStores();
    long countStores();

}
