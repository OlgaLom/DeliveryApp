package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Product;

public interface ProductService extends BaseService<Product,Long>{
    Product findBySerial(String serial);

    Product createProduct(Product product,Long categoryId);
    Product updateProduct(Product product,Long categoryId);
    Product deleteProduct(Product product);
    Product deleteProductById(Product product);
}
