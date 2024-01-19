package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.ProductCategory;

public interface ProductCategoryService extends BaseService<ProductCategory,Long>{
    ProductCategory findByDescription(String description);
    ProductCategory getProductCategoryName(String name);
}
