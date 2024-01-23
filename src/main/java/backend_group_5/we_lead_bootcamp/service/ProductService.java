package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.Variation;

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
    List<Variation> getVariationSize(String productName, Variation.Size size);
    List<Variation> getVariationFlavour(String productName, Variation.Flavours flavours);
    List<Variation> getVariationSauces(String productName, Variation.Sauces sauces);
    List<Variation> getVariationToppings(String productName, Variation.Toppings toppings);
    Store getStore(Store store);
}
