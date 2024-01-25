package backend_group_5.we_lead_bootcamp.controller;

import backend_group_5.we_lead_bootcamp.mapper.BaseMapper;
import backend_group_5.we_lead_bootcamp.mapper.StoreMapper;
import backend_group_5.we_lead_bootcamp.model.Review;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.StoreCategory;
import backend_group_5.we_lead_bootcamp.model.StoreCategoryVariation;
import backend_group_5.we_lead_bootcamp.service.BaseService;
import backend_group_5.we_lead_bootcamp.service.StoreService;
import backend_group_5.we_lead_bootcamp.transfer.ApiResponse;
import backend_group_5.we_lead_bootcamp.transfer.resource.StoreResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController extends BaseController<Store, StoreResource> {


    private final StoreService storeService;
    private final StoreMapper storeMapper;

    @Override
    protected BaseService<Store, Long> getBaseService() {
        return storeService;
    }

    @Override
    protected BaseMapper<Store, StoreResource> getMapper() {
        return storeMapper;
    }

    //Get all stores
    @GetMapping
    public ResponseEntity<ApiResponse<List<StoreResource>>> findAllStores() {
        return ResponseEntity.ok(
                ApiResponse.<List<StoreResource>>builder()
                        .data(storeMapper.toResources(storeService.findAll()))
                        .build());
    }

    // Get store by ID
    @GetMapping("/{storeId}")
    public ResponseEntity<ApiResponse<StoreResource>> getStoreById(@PathVariable("storeId") final Long storeId) {
        return ResponseEntity.ok(
                ApiResponse.<StoreResource>builder()
                        .data(storeMapper.toResource(storeService.getById(storeId)))
                        .build());
    }

    @PostMapping
    public ResponseEntity<StoreResource> createStore(@RequestBody StoreResource storeResource) {
        Store store = storeMapper.toDomain(storeResource);
        Store createdStore = storeService.createStore(store);
        StoreResource createdStoreResource = storeMapper.toResource(createdStore);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStoreResource);
    }

    @PutMapping("/{storeId}")
    public ResponseEntity<StoreResource> updateStore(@PathVariable Long storeId, @RequestBody StoreResource storeResource) {
        Store store = storeMapper.toDomain(storeResource);
        Store updatedStore = storeService.updateStore(storeId, store);
        StoreResource updatedStoreResource = storeMapper.toResource(updatedStore);
        return ResponseEntity.ok(updatedStoreResource);
    }

    @DeleteMapping("/{storeId}")
    public ResponseEntity<Void> deleteStoreById(@PathVariable Long storeId) {
        storeService.deleteStoreById(storeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<ApiResponse<List<StoreResource>>> findAllStoresByNameIgnoreCase(@PathVariable String keyword) {
        return ResponseEntity.ok(
                ApiResponse.<List<StoreResource>>builder()
                        .data(storeMapper.toResources(storeService.findAllStoresByNameIgnoreCase(keyword)))
                        .build());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<List<StoreResource>>> findAllStoresByCategory(@PathVariable StoreCategoryVariation category) {
        return ResponseEntity.ok(
                ApiResponse.<List<StoreResource>>builder()
                        .data(storeMapper.toResources(storeService.findAllStoresByCategory(category)))
                        .build());
    }

    @GetMapping("/category-rating")
    public ResponseEntity<ApiResponse<List<StoreResource>>> findStoresByCategoryAndRating(
            @RequestParam StoreCategory category,
            @RequestParam int minRating) {
        return ResponseEntity.ok(
                ApiResponse.<List<StoreResource>>builder()
                        .data(storeMapper.toResources(storeService.findStoresByCategoryAndRating(category, minRating)))
                        .build());
    }

    @GetMapping("/top-rated")
    public ResponseEntity<ApiResponse<List<StoreResource>>> findTopRatedStores(@RequestParam int limit) {
        return ResponseEntity.ok(
                ApiResponse.<List<StoreResource>>builder()
                        .data(storeMapper.toResources(storeService.findTopRatedStores(limit)))
                        .build());
    }

    @GetMapping("/min-order-amount")
    public ResponseEntity<ApiResponse<List<StoreResource>>> findStoresWithMinOrderAmount(@RequestParam BigDecimal minOrderAmount) {
        return ResponseEntity.ok(
                ApiResponse.<List<StoreResource>>builder()
                        .data(storeMapper.toResources(storeService.findStoresWithMinOrderAmount(minOrderAmount)))
                        .build());
    }

    @GetMapping("/average-rating/{storeId}")
    public ResponseEntity<ApiResponse<BigDecimal>> calculateAverageRating(@PathVariable Long storeId) {
        return ResponseEntity.ok(
                ApiResponse.<BigDecimal>builder()
                        .data(storeService.calculateAverageRating(storeId))
                        .build());
    }

    @GetMapping("/delivery-time/{storeId}")
    public ResponseEntity<ApiResponse<Integer>> getDeliveryTime(@PathVariable Long storeId) {
        return ResponseEntity.ok(
                ApiResponse.<Integer>builder()
                        .data(storeService.getDeliveryTime(storeId))
                        .build());
    }

    @PutMapping("/update-delivery-time/{storeId}/{deliveryTime}")
    public ResponseEntity<Void> updateDeliveryTime(@PathVariable Long storeId, @PathVariable Integer deliveryTime) {
        storeService.updateDeliveryTime(storeId, deliveryTime);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reviews/{storeId}")
    public ResponseEntity<ApiResponse<List<Review>>> findReviewsByStore(@PathVariable Long storeId) {
        Store store = storeService.getById(storeId);
        return ResponseEntity.ok(
                ApiResponse.<List<Review>>builder()
                        .data(store != null ? storeService.findReviewsByStore(store) : null)
                        .build());
    }


    /*@ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Void> handleNoSuchElementException(NoSuchElementException ex) {
        return ApiResponse.<Void>builder()
                .apiError(ApiError.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("Resource not found")
                        .build())
                .build();
    } vgazei ApiError
    */

}
