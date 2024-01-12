package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.ProductCategory;

public interface ProductCategoryRepository extends BaseRepository<ProductCategory,Long>{
    ProductCategory findByDescription(String description);
}
