package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Review;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.StoreCategory;
import backend_group_5.we_lead_bootcamp.model.StoreCategoryVariation;
import backend_group_5.we_lead_bootcamp.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;

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
    public List<Store> createAllStores(List<Store> stores) {
        return storeRepository.saveAll(stores);
    }

    @Override
    public Store updateStore(Long storeId, Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store getByCategory(StoreCategory category) {
        return storeRepository.findFirstByCategory(category);
    }

    @Override
    public List<Store> getStoresByCategory(StoreCategory category) {
        return storeRepository.findStoresByCategory(category);
        //expects to retrieve a list of stores that match the given category
    }

    @Override
    public Store updateStoreCategory(Long storeId, StoreCategoryVariation newCategory) {
        Store existingStore = getById(storeId);
        if (existingStore != null) {
            existingStore.setCategory(newCategory);
            return storeRepository.save(existingStore);
        }
        return null; //gia handle to not found
    }

    @Override
    public List<Store> getStoresByCategoryAndRating(StoreCategory category,int minRating){
        return storeRepository.findStoresByCategoryAndRating(category,minRating);
    }
    @Override
    public List<Store> getTopRatedStores(int limit){
        return storeRepository.findTopRatedStores((Pageable) PageRequest.of(0,limit));
    }
    @Override
    public void deleteStoreCategory(Long storeId) {}

    @Override
    public void deleteStoreById(Long storeId) {
        storeRepository.deleteById(storeId);
    }

    @Override
    public Store getStoreById(Long storeId) {
        return getById(storeId);
    }

    @Override
    public boolean doesStoreExist(Store storeId) {
        return storeRepository.existsById(storeId.getId());
    }

    @Override
    public List<Store> findAllStores() {
        return findAll();
    }

    @Override
    public long countStores() {
        return count();
    }

    @Override
    public List<Store> createAll(Store... items) {
        return null;
    }

    /*@Override
    public List<Product> getAllProductsInStore(Long storeId) {
        Store store = getById(storeId);
        return store != null ? store.getProducts(): Collections.emptyList();
    } */

    public List<Store> getStoresWithMinOrderAmount(BigDecimal minOrderAmount){
        return storeRepository.findByMinOrderAmountGreaterThanEqual(minOrderAmount);
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

    public List<Store> searchStoresByName(String keyword) {
        return storeRepository.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public List<Store> searchStoresByCategory(StoreCategory category) {
        return storeRepository.findStoresByCategory(category);
    }
}


