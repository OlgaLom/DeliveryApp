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

    @Query("SELECT pr FROM Product pr WHERE pr.sizes = :sizes")
    List<Product> getProductBySizes(Sizes sizes);

    @Query("SELECT pr FROM Product pr WHERE pr.flavours = :flavours")
    List<Product> getProductByFlavours(Flavours flavours);

    @Query("SELECT pr FROM Product pr WHERE pr.sauces = :sauces")
    List<Product> getProductBySauces(Sauces sauces);

    @Query("SELECT pr FROM Product pr WHERE pr.toppings = :toppings")
    List<Product> getProductByToppings(Toppings toppings);

    @Query("SELECT pr FROM Product pr WHERE pr.store.id = :storeId")
    List<Product> findProductsByStore(Long storeId);



}
