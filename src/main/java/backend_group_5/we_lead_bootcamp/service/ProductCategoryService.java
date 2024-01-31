package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService extends BaseService<ProductCategory,Long>{
    ProductCategory getByDescription(String description);
    List<ProductCategory> getProductCategoryName(String name);
}
