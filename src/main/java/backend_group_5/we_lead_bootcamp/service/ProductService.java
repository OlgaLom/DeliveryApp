package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Product;

import java.util.List;

public interface ProductService extends BaseService<Product,Long>{
    Product findBySerial(String serial);

//    Product create(Product product,Long categoryId);

    Product createProduct(Product product,Long categoryId);
    List<Product> createAllProducts(List<Product> products);
    void updateProduct(Product product);
    void deleteProduct(Product product);
    void deleteProductById(Long productId);
    Product getProduct(Product product);
    List<Product> listAllProducts();
    long countProducts();
}
