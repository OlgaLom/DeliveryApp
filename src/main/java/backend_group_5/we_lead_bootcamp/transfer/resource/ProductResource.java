package backend_group_5.we_lead_bootcamp.transfer.resource;

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
    private CategoryResource category;
}
