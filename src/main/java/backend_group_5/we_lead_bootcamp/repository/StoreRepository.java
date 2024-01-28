package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.Review;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.StoreCategoryVariation;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
 //   Store createStore(Store store); // Sto service impl to createStore to onomase save(JPA)
    Store getStoreByName(String name);
//    List<Store> createAllStores(List<Store> stores); // Sto service impl to createStore to onomase saveAll(JPA)
//    Store updateStore(Long storeId, Store store); // Sto service impl to createStore to onomase save(JPA)
    void deleteStoreById(Long storeId);
    List<Store> findAllStoresByNameIgnoreCase(String name);
    List<Store> findAllStoresByCategory(@NotNull(message = "Category field is required") StoreCategoryVariation category);
    @Query("SELECT st FROM Store st JOIN st.reviews rev WHERE st.category = :category GROUP BY st.name HAVING AVG(rev.rating) > :rating  ")
    List<Store> findStoresByCategoryAndRating(@NotNull(message = "Category field is required") StoreCategoryVariation category, double rating);
    @Transactional
//    @Modifying
//    @Query( "SELECT AVG(rev.rating) as rating, st.name as storeName " +
//            "FROM Store st JOIN st.reviews rev GROUP BY st.name ORDER BY rating DESC")
//    List<Object[]> findTopRatedStores(); //Pageable pageable
    @Query( "SELECT AVG(rev.rating) as rating, st.name as storeName " +
            "FROM Store st JOIN st.reviews rev GROUP BY st.name ORDER BY rating DESC")
    Page<Object[]> findTopRatedStores(Pageable pageable);

    List<Store> findStoresByMinOrderAmount(BigDecimal minOrderAmount);
    @Query("SELECT st.reviews FROM Store st WHERE st.id = :storeId")
    List<Review> findReviewsByStore(Long storeId);
    @Query( "SELECT AVG(rev.rating) FROM Store st JOIN st.reviews rev WHERE st.id = :storeId")
    BigDecimal calculateAverageRating(Long storeId);
    @Query("SELECT st.deliveryTime FROM Store st WHERE st.id = :storeId")
    Integer getDeliveryTime(Long storeId);

    @Transactional
    @Modifying
    @Query("UPDATE Store st SET st.deliveryTime = :deliveryTime WHERE st.id = :storeId")
    void updateDeliveryTime(Long storeId, Integer deliveryTime);

}
