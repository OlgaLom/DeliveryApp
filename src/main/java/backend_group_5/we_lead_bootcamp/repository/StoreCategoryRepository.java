package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.StoreCategory;
import backend_group_5.we_lead_bootcamp.model.StoreCategoryVariation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StoreCategoryRepository extends JpaRepository<StoreCategory, Long> {
    @Query("SELECT st FROM StoreCategory st WHERE st.displayName = :name")
    Optional<StoreCategory> findByName(StoreCategoryVariation name);
}

