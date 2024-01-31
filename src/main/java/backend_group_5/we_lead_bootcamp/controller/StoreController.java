package backend_group_5.we_lead_bootcamp.controller;

import backend_group_5.we_lead_bootcamp.mapper.BaseMapper;
import backend_group_5.we_lead_bootcamp.mapper.StoreMapper;
import backend_group_5.we_lead_bootcamp.model.*;
import backend_group_5.we_lead_bootcamp.model.enums.StoreCategoryVariation;
import backend_group_5.we_lead_bootcamp.service.BaseService;
import backend_group_5.we_lead_bootcamp.service.StoreService;
import backend_group_5.we_lead_bootcamp.transfer.ApiResponse;
import backend_group_5.we_lead_bootcamp.transfer.resource.StoreResource;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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


    @PostMapping("/create")
    public ResponseEntity<String> createStore(@RequestBody Store store) {
        if (!storeService.storeExists(store)) {
            Store newStore = storeService.createStore(store);
            return new ResponseEntity<>("Store created with ID: " + newStore.getId(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Store with similar values already exists. Skipping creation.", HttpStatus.BAD_REQUEST);
        }
    }

//    @PutMapping("/updateStore")
//    public ResponseEntity<StoreResource> updateStore( @RequestBody StoreResource storeResource) {
//        Store store = storeMapper.toDomain(storeResource);
//        Store updatedStore = storeService.updateStore(store);
//        StoreResource updatedStoreResource = storeMapper.toResource(updatedStore);
//        return ResponseEntity.ok(updatedStoreResource);
//    }

//"data": [], (coffee)
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

    @GetMapping("/top-rated")
    public ResponseEntity<ApiResponse<List<StoreResource>>> findTopRatedStores(
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) StoreCategoryVariation category) {

        List<Object[]> topRatedStores = storeService.findTopRatedStores(limit, category);
        List<StoreResource> storeResources = topRatedStores.stream()
                .map(array -> {
                    Double rating = (Double) array[0];
                    Store store = (Store) array[1];
                    StoreResource storeResource = storeMapper.toResource(store);
                    storeResource.setRating(rating);
                    return storeResource;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                ApiResponse.<List<StoreResource>>builder()
                        .data(storeResources)
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

}
