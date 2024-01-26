package backend_group_5.we_lead_bootcamp.transfer.resource;

import backend_group_5.we_lead_bootcamp.model.enums.Flavours;
import backend_group_5.we_lead_bootcamp.model.enums.Sauces;
import backend_group_5.we_lead_bootcamp.model.enums.Sizes;
import backend_group_5.we_lead_bootcamp.model.enums.Toppings;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

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
    private ProductCategoryResource category;
    private Sizes size;
    private Flavours flavours;
    private Sauces sauces;
    private Toppings toppings;

}
