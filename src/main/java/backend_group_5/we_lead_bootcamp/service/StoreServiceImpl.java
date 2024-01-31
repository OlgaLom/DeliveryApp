package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.Review;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.enums.StoreCategoryVariation;
import backend_group_5.we_lead_bootcamp.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
    public Store createStore(Store store) {
        return storeRepository.save(store);
    }
    @Override
    public Store getStoreByName(String name) {
        return storeRepository.getStoreByName(name);
    }
    @Override
    public Store getById(Long id) {
        return getRepository().getReferenceById(id);
    }

    @Override
    public List<Store> createAllStores(List<Store> stores) {
        return storeRepository.saveAll(stores);
    }

//    @Override
//    public Store updateStore( Store store){
//        return storeRepository.save(store);
//    }

    @Override
    public void deleteStoreById(Long storeId) {
    }

    public List<Store> findAllStoresByNameIgnoreCase(String keyword) {
        return storeRepository.findAllStoresByNameIgnoreCase(keyword);
    }

    @Override
    public List<Store> findAllStoresByCategory(StoreCategoryVariation category) {
        return storeRepository.findAllStoresByCategory(category);
    }

    @Override
    public List<Object[]> findTopRatedStores(int limit, StoreCategoryVariation category) {
        return storeRepository.findTopRatedStoresByCategory(category)
            .stream()
            .limit(limit)
            .collect(Collectors.toList());
    }

    public List<Store> findStoresWithMinOrderAmount(BigDecimal minOrderAmount){
        return storeRepository.findStoresByMinOrderAmount(minOrderAmount);
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

    @Override
    public List<Review> findReviewsByStore(Store store) {
        return storeRepository.findReviewsByStore(store.getId());
    }

    // Add the storeExists method
    @Override
    public boolean storeExists(Store store) {
        Store existingStore = getStoreByName(store.getName());
        return existingStore != null && !existingStore.getId().equals(store.getId());
    }

    @Override
    public void addReviewToStore(Long storeId, Review review) {
        Store store = getById(storeId);
        if (store != null) {
            List<Review> reviews = store.getReviews();
            reviews.add(review);
            review.setStore(store);
            update(store);
        }
    }


    /*show all products in store (?)
    @Override
    public List<Product> getAllProductsInStore(Long storeId) {
        Store store = getById(storeId);
        return store != null ? store.getProducts(): Collections.emptyList();
    } */
}


