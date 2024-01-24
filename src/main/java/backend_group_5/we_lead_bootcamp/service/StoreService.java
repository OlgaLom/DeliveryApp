package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.StoreCategory;
import backend_group_5.we_lead_bootcamp.model.StoreCategoryVariation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface StoreService extends BaseService<Store, Long> {

    Store getStoreByName(String name);

    List<Store> findStoresByName(String name);
    List<Store> getStoresByCategory(StoreCategory category);

    Store getByCategory(StoreCategory category);

    List<Store> findStoresByCategory(StoreCategory category);
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

