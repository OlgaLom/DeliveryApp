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

    Store updateStore(Long storeId, Store store);
    Store updateStoreCategory(Long storeId, StoreCategoryVariation newCategory);

    void deleteStoreCategory(Long storeId);

    void deleteStoreById(Long storeId);

    Store getStoreById(Long storeId);

    Store getByCategory(StoreCategory category);

    boolean doesStoreExist(Store store);

    List<Store> findAllStores();

    long countStores();

    List<Store> searchStoresByName(String name);
    List<Store> searchStoresByCategory(StoreCategory category);

    List<Store> getStoresByCategoryAndRating(StoreCategory category, int minRating);
    List<Store> getTopRatedStores(int limit);

    //List<Product> getAllProductsInStore(Long storeId);

    BigDecimal calculateAverageRating(Long storeId);

    Integer getDeliveryTime(Long storeId);
    void updateDeliveryTime(Long storeId, Integer deliveryTime);

    List<Store> getStoresWithMinOrderAmount(BigDecimal minOrderAmount);


    //optional feature giati thelei douleia me DataBase extension (?)
    // List<Store> getStoresWithinDistance(BigDecimal latitude, BigDecimal longitude, double maxDistance);

    // this is optional depending on the structure of the Order-related method gia elaxisti paraggelia
    // BigDecimal calculateTotalOrderAmount(Store store);
}

