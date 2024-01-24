package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Review;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.StoreCategory;
import backend_group_5.we_lead_bootcamp.model.StoreCategoryVariation;
import backend_group_5.we_lead_bootcamp.repository.StoreRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl extends BaseServiceImpl<Store> implements StoreService {
    private final StoreRepository storeRepository;
    @Override
    protected JpaRepository<Store, Long> getRepository() {
        return storeRepository;
    }
    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Store getStoreByName(String name) {
        return storeRepository.getStoreByName(name);
    }

    public List<Store> findStoresByName(String keyword) {
        return storeRepository.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public List<Store> findStoresByCategory(StoreCategory category) {
        return storeRepository.findStoresByCategory(category.getDisplayName());
    }

    @Override
    public List<Store> findStoresByCategoryAndRating(StoreCategory category,int minRating){
        return storeRepository.findStoresByCategoryAndRating(category.getDisplayName(),minRating);
    }

    @Override
    public List<Store> findTopRatedStores(int limit){
        return storeRepository.findTopRatedStores((Pageable) PageRequest.of(0,limit));
    }
    public List<Store> getStoresWithMinOrderAmount(BigDecimal minOrderAmount){
        return storeRepository.findByMinOrderAmountGreaterThanEqual(minOrderAmount);
    }

    @Override
    public Store createStore(Store store) {
        return null;
    }

    @Override
    public List<Store> createAllStores(List<Store> stores) {
        return null;
    }

    @Override
    public Store updateStore(Long storeId, Store store) {
        return null;
    }

    @Override
    public void deleteStoreById(Long storeId) {

    }

    public BigDecimal calculateAverageRating(Long storeId){
        Store store = getById(storeId);
        if (store != null) {
            List<Review> reviews=store.getReviews();
            if (!reviews.isEmpty()) {
                double averageRating = reviews.stream()
                        .mapToDouble(Review::getRating)
                        .average()
                        .orElse(0.0);
                return BigDecimal.valueOf(averageRating);
            }
        }
        return BigDecimal.ZERO;
    }

    @Override
    public Integer getDeliveryTime(Long storeId) {
        Store store =getById(storeId);
        return store != null ? store.getDeliveryTime() : null;
    }

    @Override
    public void updateDeliveryTime(Long storeId, Integer deliveryTime) {
        Store store =getById(storeId);
        if (store != null){
            store.setDeliveryTime(deliveryTime);
            storeRepository.save(store);
        }
    }
    /*@Override
    public List<Product> getAllProductsInStore(Long storeId) {
        Store store = getById(storeId);
        return store != null ? store.getProducts(): Collections.emptyList();
    } */
}


