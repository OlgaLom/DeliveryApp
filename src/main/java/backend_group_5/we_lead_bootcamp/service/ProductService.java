package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.enums.Flavours;
import backend_group_5.we_lead_bootcamp.model.enums.Sauces;
import backend_group_5.we_lead_bootcamp.model.enums.Sizes;
import backend_group_5.we_lead_bootcamp.model.enums.Toppings;

import java.math.BigDecimal;

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
    Sizes getVariationSize(String productName, Sizes size);

    //get Variation flavours
    Flavours getVariationFlavour(String productName, Flavours flavours);

    //get variation sauces
    Sauces getVariationSauces(String productName, Sauces sauces);

    //get variation toppings
    Toppings getVariationToppings(String productName, Toppings toppings);

    //get store
    Store getStore(Store store);
}
