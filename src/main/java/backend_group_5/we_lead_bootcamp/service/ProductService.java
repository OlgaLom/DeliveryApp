package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.ProductVariations;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService extends BaseService<Product,Long>{
    Product findBySerial(String serial);
    Product createProduct(Product product,Long categoryId);
    List<Product> listAllProducts();
    void updateProduct(Product product);
    void deleteProduct(Product product);
    void deleteProductById(Long productId);
    long countProducts();
    boolean productExists(Product product);
    Product getProductById(Product product,Long id);
    Product getProductName(String name,Long id);
    Product getProductPrice(BigDecimal price);
    Product getProductDescription(String description);
    List<ProductVariations> getVariationSize(String productName, ProductVariations.Size size);
    List<ProductVariations> getVariationFlavour(String productName, ProductVariations.Flavours flavours);
    List<ProductVariations> getVariationSauces(String productName, ProductVariations.Sauces sauces);
    List<ProductVariations> getVariationToppings(String productName, ProductVariations.Toppings toppings);
    Store getStore(Store store);
}
