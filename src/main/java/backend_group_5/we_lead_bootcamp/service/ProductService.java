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
    Sizes getVariationSize(Sizes size, String productName);

    //get Variation flavours
    Flavours getVariationFlavour( Flavours flavours, String productName);

    //get variation sauces
    Sauces getVariationSauces(Sauces sauces, String productName );

    //get variation toppings
    Toppings getVariationToppings(Toppings toppings,String productName);

    //get store
    Store getStore(Store store);
}
