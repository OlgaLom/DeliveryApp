package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.enums.Flavours;
import backend_group_5.we_lead_bootcamp.model.enums.Sauces;
import backend_group_5.we_lead_bootcamp.model.enums.Sizes;
import backend_group_5.we_lead_bootcamp.model.enums.Toppings;
import backend_group_5.we_lead_bootcamp.transfer.resource.ProductResource;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product getProductBySerial(final String serial);
    Product getProductByDescription(final String description);
    Product getProductByName(final String productName);
    Product getProductByPrice(final BigDecimal productPrice);
    @Query("SELECT pr FROM Product pr WHERE pr.sizes = :sizes AND pr.name LIKE %:productName%")
    List<ProductResource> getVariationBySizes(final String productName, Sizes sizes);
    @Query("SELECT pr FROM Product pr WHERE pr.flavours = :flavours AND pr.name = :productName")
    Flavours getVariationByFlavours(Flavours flavours, final String productName);
    @Query("SELECT pr FROM Product pr WHERE pr.sauces = :sauces AND pr.name = :productName")
    Sauces getVariationBySauces(Sauces sauces,final String productName);
    @Query("SELECT pr FROM Product pr WHERE pr.toppings = :toppings AND pr.name = :productName")
    Toppings getProductByToppings(Toppings toppings, final String productName);
    @Query("SELECT pr FROM Product pr WHERE pr.store.id = :storeId")
    List<Product> findProductsByStore(Long storeId);

}
