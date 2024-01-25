package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.Review;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.StoreCategoryVariation;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Store createStore(Store store);
    Store getStoreByName(String name);
    List<Store> createAllStores(List<Store> stores);
    Store updateStore(Long storeId, Store store);
    void deleteStoreById(Long storeId);
    List<Store> findAllStoresByNameIgnoreCase(String name);
    List<Store> findAllStoresByCategory(@NotNull(message = "Category field is required") StoreCategoryVariation category);
    List<Store> findStoresByCategoryAndRating(@NotNull(message = "Category field is required") StoreCategoryVariation category, double rating);
    List<Store> findTopRatedStores(Pageable pageable);
    List<Store> findStoresByMinOrderAmount(BigDecimal minOrderAmount);

    List<Review> findReviewsByStore(Store store);

    BigDecimal calculateAverageRating(Long storeId);
    Integer getDeliveryTime(Long storeId);
    void updateDeliveryTime(Long storeId, Integer deliveryTime);

}
