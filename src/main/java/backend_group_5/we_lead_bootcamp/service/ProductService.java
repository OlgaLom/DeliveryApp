package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.enums.Flavours;
import backend_group_5.we_lead_bootcamp.model.enums.Sauces;
import backend_group_5.we_lead_bootcamp.model.enums.Sizes;
import backend_group_5.we_lead_bootcamp.model.enums.Toppings;
import backend_group_5.we_lead_bootcamp.transfer.resource.ProductResource;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService extends BaseService<Product,Long>{

    //find By Serial
    Product findBySerial(String serial);

    //create Product
    Product create(Product product,Long productCategoryId);

    //get product by name
    Product getProductName(String name);

    //get product by price
    Product getProductPrice(BigDecimal price);

    //get product by description
    Product getProductDescription(String description);

    //get variation size
    List<Product> getProductBySize(Sizes sizes);

    //get Variation flavours
    List<Product> getProductByFlavour( Flavours flavours);

    //get variation sauces
    List<Product> getProductBySauces(Sauces sauces);

    //get variation toppings
    List<Product> getProductByToppings(Toppings toppings);

    //get store
    List<Product> findProductsByStore(Long storeId);
}
