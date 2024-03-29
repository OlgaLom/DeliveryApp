package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Review;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.enums.StoreCategoryVariation;

import java.math.BigDecimal;
import java.util.List;

public interface StoreService extends BaseService<Store, Long> {
    Store createStore(Store store);
    Store getStoreByName(String name);
    List<Store> createAllStores(List<Store> stores);
    //Store updateStore( Store store);
    void deleteStoreById(Long storeId);
    List<Store> findAllStoresByNameIgnoreCase(String name);
    //specified keyword many stores - for the lowercase (e.g. CafeShop and cafeShop)

    List<Store> findAllStoresByCategory(StoreCategoryVariation category);
    List<Object[]> findTopRatedStores(int limit, StoreCategoryVariation category);
    List<Store> findStoresWithMinOrderAmount(BigDecimal minOrderAmount);
    //List<Product> getAllProductsInStore(Long storeId);
    BigDecimal calculateAverageRating(Long storeId);
    Integer getDeliveryTime(Long storeId);
    void updateDeliveryTime(Long storeId, Integer deliveryTime);
    List<Review> findReviewsByStore(Store store);
    boolean storeExists(Store store);
    void addReviewToStore(Long storeId, Review review);

    //this is an optional additional feature because it needs specific PostalCodes perhaps
    // List<Store> getStoresWithinDistance(BigDecimal latitude, BigDecimal longitude, double maxDistance);

}

