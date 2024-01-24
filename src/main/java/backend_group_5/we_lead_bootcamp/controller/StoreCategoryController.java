package backend_group_5.we_lead_bootcamp.controller;

import backend_group_5.we_lead_bootcamp.mapper.BaseMapper;
import backend_group_5.we_lead_bootcamp.mapper.StoreCategoryMapper;
import backend_group_5.we_lead_bootcamp.model.StoreCategory;
import backend_group_5.we_lead_bootcamp.service.BaseService;
import backend_group_5.we_lead_bootcamp.service.StoreCategoryService;
import backend_group_5.we_lead_bootcamp.transfer.ApiError;
import backend_group_5.we_lead_bootcamp.transfer.resource.StoreCategoryResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/store-categories")
@RequiredArgsConstructor
public class StoreCategoryController extends BaseController<StoreCategory, StoreCategoryResource> {

    private final StoreCategoryService storeCategoryService;
    private final StoreCategoryMapper storeCategoryMapper;

    @Override
    protected BaseService<StoreCategory, Long> getBaseService() {
        return storeCategoryService;
    }

    @Override
    protected BaseMapper<StoreCategory, StoreCategoryResource> getMapper() {
        return storeCategoryMapper;
    }

    @GetMapping
    public ResponseEntity<List<StoreCategoryResource>> getAllStoreCategories() {
        List<StoreCategoryResource> categoryResources = storeCategoryService.findAllCategories()
                .stream()
                .map(storeCategoryMapper::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categoryResources);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<StoreCategoryResource> getStoreCategoryById(@PathVariable Long categoryId) {
        StoreCategoryResource categoryResource = storeCategoryMapper.toResource(storeCategoryService.getCategoryById(categoryId));
        return ResponseEntity.ok(categoryResource);
    }

    @PostMapping
    public ResponseEntity<StoreCategoryResource> createStoreCategory(@RequestBody StoreCategoryResource categoryResource) {
        StoreCategory category = storeCategoryMapper.toDomain(categoryResource);
        StoreCategory createdCategory = storeCategoryService.createCategory(category);
        StoreCategoryResource createdCategoryResource = storeCategoryMapper.toResource(createdCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoryResource);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<StoreCategoryResource> updateStoreCategory(@PathVariable Long categoryId, @RequestBody StoreCategoryResource categoryResource) {
        StoreCategory category = storeCategoryMapper.toDomain(categoryResource);
        StoreCategory updatedCategory = storeCategoryService.updateCategory(categoryId, category.getDisplayName());
        StoreCategoryResource updatedCategoryResource = storeCategoryMapper.toResource(updatedCategory);
        return ResponseEntity.ok(updatedCategoryResource);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteStoreCategory(@PathVariable Long categoryId) {
        storeCategoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

}
