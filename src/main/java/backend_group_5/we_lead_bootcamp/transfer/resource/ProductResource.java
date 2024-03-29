package backend_group_5.we_lead_bootcamp.transfer.resource;

import backend_group_5.we_lead_bootcamp.model.ProductCategory;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.enums.Flavours;
import backend_group_5.we_lead_bootcamp.model.enums.Sauces;
import backend_group_5.we_lead_bootcamp.model.enums.Sizes;
import backend_group_5.we_lead_bootcamp.model.enums.Toppings;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
public class ProductResource extends BaseResource {
    @NotNull
    private String name;
    @NotNull
    private String serial;
    @NotNull
    private BigDecimal price;
    @NotNull
    private ProductCategory productCategory;
    @NotNull
    private Store store;
    private Sizes sizes;
    private Flavours flavours;
    private Sauces sauces;
    private Toppings toppings;

}
