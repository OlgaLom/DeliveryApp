package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.StoreCategory;
import backend_group_5.we_lead_bootcamp.model.StoreCategoryVariation;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findStoresByCategory(@NotNull(message = "Category field is required") StoreCategoryVariation category);

    Store getStoreByName(String name);
    List<Store> findByNameContainingIgnoreCase(String keyword);

    Store findFirstByCategory(@NotNull(message = "Category field is required") StoreCategoryVariation category);

    List<Store> findStoresByCategoryAndRating(@NotNull(message = "Category field is required") StoreCategoryVariation category, double rating);

    List<Store> findTopRatedStores(Pageable pageable);

    List<Store> findByMinOrderAmountGreaterThanEqual(BigDecimal minOrderAmount);

    //List<Review> findReviewsByStore(Store store);


}
