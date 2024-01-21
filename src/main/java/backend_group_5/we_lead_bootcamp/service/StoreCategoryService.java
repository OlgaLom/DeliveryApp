package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.StoreCategory;

import java.util.List;


public interface StoreCategoryService extends BaseService<StoreCategory, Long> {
    StoreCategory createCategory(StoreCategory category);

    List<StoreCategory> getAllCategories();

    //more methods in the future
}