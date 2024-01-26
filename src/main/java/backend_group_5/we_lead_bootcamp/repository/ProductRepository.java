package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.enums.Flavours;
import backend_group_5.we_lead_bootcamp.model.enums.Sauces;
import backend_group_5.we_lead_bootcamp.model.enums.Sizes;
import backend_group_5.we_lead_bootcamp.model.enums.Toppings;
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
    @Query("SELECT pr FROM Product pr WHERE pr.sizes = :size AND pr.name = :productName")
    Sizes getVariationBySizes(Sizes size, final String productName);
    @Query("SELECT pr FROM Product pr WHERE pr.flavours = :flavours AND pr.name = :productName")
    Flavours getVariationByFlavours(Flavours flavours, final String productName);
    @Query("SELECT pr FROM Product pr WHERE pr.sauces = :sauces AND pr.name = :productName")
    Sauces getVariationBySauces(Sauces sauces,final String productName);
    @Query("SELECT pr FROM Product pr WHERE pr.toppings = :toppings AND pr.name = :productName")
    Toppings getVariationByToppings(Toppings toppings, final String productName);
}
