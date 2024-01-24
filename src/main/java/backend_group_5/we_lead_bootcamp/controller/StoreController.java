package backend_group_5.we_lead_bootcamp.controller;

import backend_group_5.we_lead_bootcamp.mapper.BaseMapper;
import backend_group_5.we_lead_bootcamp.mapper.StoreMapper;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.service.BaseService;
import backend_group_5.we_lead_bootcamp.service.StoreService;
import backend_group_5.we_lead_bootcamp.transfer.ApiResponse;
import backend_group_5.we_lead_bootcamp.transfer.resource.StoreResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Void> deleteStore(@PathVariable Long storeId) {
        storeService.deleteStoreById(storeId);
        return ResponseEntity.noContent().build();
    }

}
