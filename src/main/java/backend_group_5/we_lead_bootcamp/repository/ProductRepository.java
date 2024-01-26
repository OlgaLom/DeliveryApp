package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.enums.Flavours;
import backend_group_5.we_lead_bootcamp.model.enums.Sauces;
import backend_group_5.we_lead_bootcamp.model.enums.Sizes;
import backend_group_5.we_lead_bootcamp.model.enums.Toppings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getProductBySerial(final String serial);
    Product getProductByDescription(final String description);
    Product getProductByName(final String productName);
    Product getProductByPrice(final BigDecimal productPrice);
    Sizes getVariationBySizes(final String productName,Sizes size);
    Flavours getVariationByFlavours(final String productName,Flavours flavours);
    Sauces getVariationBySauces(final String productName,Sauces sauces);
    Toppings getVariationByToppings(final String productName,Toppings toppings);
}
