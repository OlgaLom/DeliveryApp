package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.ProductCategory;

public interface ProductCategoryService extends BaseService<ProductCategory,Long>{
    ProductCategory getByDescription(String description);
    ProductCategory getProductCategoryName(ProductCategory name);
}
