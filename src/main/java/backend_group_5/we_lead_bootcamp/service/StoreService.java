package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.StoreCategory;
import backend_group_5.we_lead_bootcamp.model.StoreCategoryVariation;

import java.math.BigDecimal;
import java.util.List;

public interface StoreService extends BaseService<Store, Long> {

    List<Store> getStoresByCategory(StoreCategory category);

    Store createStore(Store store);

    List<Store> createAllStores(List<Store> stores);

    Store updateStoreCategory(Long storeId, StoreCategoryVariation newCategory);

    void deleteStoreCategory(Long storeId);

    void deleteStoreById(Long storeId);

    Store getStoreById(Long storeId);

    Store getByCategory(StoreCategory category);

    boolean doesStoreExist(Store store);

    List<Store> findAllStores();

    long countStores();

    BigDecimal calculateTotalOrderAmount(Store store);
}

