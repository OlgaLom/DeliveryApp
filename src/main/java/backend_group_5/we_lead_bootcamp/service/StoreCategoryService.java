package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.StoreCategory;
import backend_group_5.we_lead_bootcamp.model.StoreCategoryVariation;

import java.util.List;
import java.util.Optional;


public interface StoreCategoryService extends BaseService<StoreCategory, Long> {
    StoreCategory createCategory(StoreCategory category);
    Optional<StoreCategory> getStoreCategoryByName(StoreCategoryVariation name);
    StoreCategory updateCategory(Long categoryId, StoreCategoryVariation newName);


    void deleteCategory(Long categoryId);
    void deleteCategoryByName(StoreCategoryVariation name);

    StoreCategory getCategoryById(Long categoryId);

    boolean doesCategoryExist(Long categoryId);

    List<StoreCategory> createAllCategories(List<StoreCategory> categories);

    List<StoreCategory> findAllCategories();
    long countCategories();


    //more methods in the future
}