package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.Variation;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends BaseRepository<Product, Long> {
    Product findBySerial(final String serial);
    Product findByDescription(final String description);
    Product findByName(final String productName);
    Product findByPrice(final BigDecimal productPrice);
    List<Variation> getVariationBySize(final String productName, Variation.Size size);
    List<Variation> getVariationByFlavours(final String productName, Variation.Flavours flavours);
    List<Variation> getVariationBySauces(final String productName, Variation.Sauces sauces);
    List<Variation> getVariationByToppings(final String productName, Variation.Toppings toppings);
}
