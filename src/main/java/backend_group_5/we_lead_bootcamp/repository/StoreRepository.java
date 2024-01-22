package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findStoresByCategory(StoreCategory category);

    Store findFirstByCategory(StoreCategory category);

    List<Store> findStoresByCategoryAndRating(StoreCategory category,int minRating);

    List<Store> findTopRatedStores(Pageable pageable);

    List<Store> findByMinOrderAmountGreaterThanEqual(BigDecimal minOrderAmount);

    //List<Review> findReviewsByStore(Store store);

    List<Store> findByNameContainingIgnoreCase(String keyword);

}
