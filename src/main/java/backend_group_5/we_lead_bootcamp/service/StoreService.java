package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Review;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.StoreCategory;
import backend_group_5.we_lead_bootcamp.model.StoreCategoryVariation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface StoreService extends BaseService<Store, Long> {
    Store createStore(Store store);
    Store getStoreByName(String name);
    List<Store> createAllStores(List<Store> stores);
    Store updateStore(Long storeId, Store store);
    void deleteStoreById(Long storeId);
    List<Store> findAllStoresByNameIgnoreCase(String name);
    //specified keyword polla stores (px ksekinane me idia grammata)
    List<Store> findAllStoresByCategory(StoreCategoryVariation category);
    //stores ana kathgoria
    List<Store> findStoresByCategoryAndRating(StoreCategory category, int minRating);
    //dinei ta top stores me limit px top10
    List<Store> findTopRatedStores(int limit);
    List<Store> findStoresWithMinOrderAmount(BigDecimal minOrderAmount);
    //List<Product> getAllProductsInStore(Long storeId);
    BigDecimal calculateAverageRating(Long storeId);
    Integer getDeliveryTime(Long storeId);
    void updateDeliveryTime(Long storeId, Integer deliveryTime);
    List<Review> findReviewsByStore(Store store);

    //optional feature giati thelei douleia me DataBase extension (?)
    // List<Store> getStoresWithinDistance(BigDecimal latitude, BigDecimal longitude, double maxDistance);

    // this is optional depending on the structure of the Order-related method gia elaxisti paraggelia
    // BigDecimal calculateTotalOrderAmount(Store store);
}

