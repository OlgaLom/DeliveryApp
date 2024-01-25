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
        return storeRepository.createStore(store);
    }
    @Override
    public Store getStoreByName(String name) {
        return storeRepository.getStoreByName(name);
    }

    @Override
    public List<Store> createAllStores(List<Store> stores) {
        return storeRepository.createAllStores(stores);
    }

    @Override
    public Store updateStore(Long storeId, Store store) {
        return storeRepository.updateStore(storeId, store);
    }

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
    public List<Store> findStoresByCategoryAndRating(StoreCategory category,int minRating){
        return storeRepository.findStoresByCategoryAndRating(category.getDisplayName(),minRating);
    }

    //Pageable - PageReq (JPA) Pagination is a technique used to limit the number of results returned from a query
    // and to provide the client with the ability to request more results as needed.
    // Pageable - info about the page
    //PageRequest - Request for the (e.g) 1st page, with number limit in items per page
    @Override
    public List<Store> findTopRatedStores(int limit){
        return storeRepository.findTopRatedStores((Pageable) PageRequest.of(0,limit));
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
        return storeRepository.findReviewsByStore(store);
    }


    /*show all products in store (?)
    @Override
    public List<Product> getAllProductsInStore(Long storeId) {
        Store store = getById(storeId);
        return store != null ? store.getProducts(): Collections.emptyList();
    } */
}


