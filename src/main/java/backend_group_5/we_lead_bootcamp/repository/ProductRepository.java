package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.ProductVariations;
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
    ProductVariations getVariationBySizes(final String productName, ProductVariations.Sizes size);
    ProductVariations getVariationByFlavours(final String productName, ProductVariations.Flavours flavours);
    ProductVariations getVariationBySauces(final String productName, ProductVariations.Sauces sauces);
    ProductVariations getVariationByToppings(final String productName, ProductVariations.Toppings toppings);
}
