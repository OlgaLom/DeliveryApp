package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.ProductCategory;

import java.util.List;

public interface ProductCategoryRepository extends BaseRepository<ProductCategory,Long>{
    ProductCategory findByDescription(String description);
    ProductCategory findByName(ProductCategory name);
    List<ProductCategory> findProductByName(ProductCategory name);
}
