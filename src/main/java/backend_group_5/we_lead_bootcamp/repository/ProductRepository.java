package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.ProductVariations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findBySerial(final String serial);
    Product findByDescription(final String description);
    Product findByName(final String productName);
    Product findByPrice(final BigDecimal productPrice);
    List<ProductVariations> getVariationBySize(final String productName, ProductVariations.Size size);
    List<ProductVariations> getVariationByFlavours(final String productName, ProductVariations.Flavours flavours);
    List<ProductVariations> getVariationBySauces(final String productName, ProductVariations.Sauces sauces);
    List<ProductVariations> getVariationByToppings(final String productName, ProductVariations.Toppings toppings);
}
