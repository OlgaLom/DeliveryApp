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

    //~~~~~TOTAL ENDPOINTS: 12~~~~~~~~~~~~~~~~//
    //~~~~~ENDPOINTS THAT WORKING: 8~~~~~~~~~~//
    //~~~~~ENDPOINTS RETURNING EMPTY DATA:1~~~//
    //~~~~~ENDPOINTS WITH ERRORS:3~~~~~~~~~~~~//


    @PostMapping("/create")
    public ResponseEntity<String> createStore(@RequestBody Store store) {
        if (!storeService.storeExists(store)) {
            Store newStore = storeService.createStore(store);
            return new ResponseEntity<>("Store created with ID: " + newStore.getId(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Store with similar values already exists. Skipping creation.", HttpStatus.BAD_REQUEST);
        }
    }

    //    @PostMapping
//    public ResponseEntity<StoreResource> createStore(@RequestBody StoreResource storeResource) {
//        Store store = storeMapper.toDomain(storeResource);
//        Store createdStore = storeService.createStore(store);
//        StoreResource createdStoreResource = storeMapper.toResource(createdStore);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdStoreResource);
//    }

    //500ari could not commit JPA transaction
//    @Transactional
    @PutMapping("/updateStore")
    public ResponseEntity<StoreResource> updateStore( @RequestBody StoreResource storeResource) {
        Store store = storeMapper.toDomain(storeResource);
        Store updatedStore = storeService.updateStore(store);
        StoreResource updatedStoreResource = storeMapper.toResource(updatedStore);
        return ResponseEntity.ok(updatedStoreResource);
    }

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

    //"status": 400,
    //"message": "Required request parameter 'category' for method parameter type StoreCategory is not present",
//    @GetMapping("/category-rating")
//    public ResponseEntity<ApiResponse<List<StoreResource>>> findStoresByCategoryAndRating(
//            @RequestParam StoreCategory category,
//            @RequestParam int rating) {
//        return ResponseEntity.ok(
//                ApiResponse.<List<StoreResource>>builder()
//                        .data(storeMapper.toResources(storeService.findStoresByCategoryAndRating(category, rating)))
//                        .build());
//    }

   //"status": 500,
   //"message": "class java.lang.Double cannot be cast to class backend_group_5.we_lead_bootcamp.model.Store
   // (java.lang.Double is in module java.base of loader 'bootstrap'; backend_group_5.we_lead_bootcamp.model.Store is in unnamed module of loader 'app')",
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

//        List<Object[]> findTopRatedStores = storeService.findTopRatedStores(limit);
//
//        List<StoreResource> stResources = findTopRatedStores.stream()
//                .map(orderArray -> (Store) orderArray[0])  // Explicitly cast Object[] to Order
//                .map(storeMapper::toResource)
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(
//                ApiResponse.<List<StoreResource>>builder()
//                        .data(stResources)
//                        .build());

//UPDATE: WORKS FOR 5 (SET ON DUMMY)-> http://localhost:8080/stores/min-order-amount?minOrderAmount=5
//"status": 400,
//"message": "Required request parameter 'minOrderAmount' for method parameter type BigDecimal is not present"
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
    //UPDATE: OK
    //"status": 500,
    //"message": "Could not write JSON: failed to lazily initialize a collection of role:
    // backend_group_5.we_lead_bootcamp.model.Store.products: could not initialize proxy - no Session"
    @GetMapping("/reviews/{storeId}")
    public ResponseEntity<ApiResponse<List<Review>>> findReviewsByStore(@PathVariable Long storeId) {
        Store store = storeService.getById(storeId);
        return ResponseEntity.ok(
                ApiResponse.<List<Review>>builder()
                        .data(store != null ? storeService.findReviewsByStore(store) : null)
                        .build());
    }


   // OTHER METHODS
    //Get all stores
//    @GetMapping
//    public ResponseEntity<ApiResponse<List<StoreResource>>> findAllStores() {
//        return ResponseEntity.ok(
//                ApiResponse.<List<StoreResource>>builder()
//                        .data(storeMapper.toResources(storeService.findAll()))
//                        .build());
//    }

    // UPDATE: LITHIKE ME COMMENT OUT : CONFLICT ME BASE
//    // Get store by ID
//    @GetMapping("/{storeId}")
//    public ResponseEntity<ApiResponse<StoreResource>> getStoreById(@PathVariable("storeId") final Long storeId) {
//        return ResponseEntity.ok(
//                ApiResponse.<StoreResource>builder()
//                        .data(storeMapper.toResource(storeService.getById(storeId)))
//                        .build());
//    }

    //UPDATE GIA DELETE : LITHIKE ME COMMENT OUT GT CONFLICT ME BASE
// "status": 500,
// "message": "Ambiguous handler methods mapped for '/stores/61':
// {public org.springframework.http.ResponseEntity backend_group_5.we_lead_bootcamp.controller.StoreController.getStoreById(java.lang.Long),
// public org.springframework.http.ResponseEntity backend_group_5.we_lead_bootcamp.controller.BaseController.get(java.lang.Long)}",
//    @DeleteMapping("/{storeId}")
//    public ResponseEntity<Void> deleteStoreById(@PathVariable Long storeId) {
//        storeService.deleteStoreById(storeId);
//        return ResponseEntity.noContent().build();
//    }


    /*@ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Void> handleNoSuchElementException(NoSuchElementException ex) {
        return ApiResponse.<Void>builder()
                .apiError(ApiError.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("Resource not found")
                        .build())
                .build();
    } gets ApiError
    */


}
