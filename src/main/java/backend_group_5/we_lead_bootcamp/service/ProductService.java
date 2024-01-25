package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.ProductVariations;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService extends BaseService<Product,Long>{

    //find By Serial
    Product findBySerial(String serial);

    //create Product
    Product createProduct(Product product,Long categoryId);

    //get product by name
    Product getProductName(String name);

    //get product by price
    Product getProductPrice(BigDecimal price);

    //get product by description
    Product getProductDescription(String description);

    //get variation size
    ProductVariations getVariationSize(String productName, ProductVariations.Sizes size);

    //get Variation flavours
    ProductVariations getVariationFlavour(String productName, ProductVariations.Flavours flavours);

    //get variation sauces
    ProductVariations getVariationSauces(String productName, ProductVariations.Sauces sauces);

    //get variation toppings
    ProductVariations getVariationToppings(String productName, ProductVariations.Toppings toppings);

    //get store
    Store getStore(Store store);
}
