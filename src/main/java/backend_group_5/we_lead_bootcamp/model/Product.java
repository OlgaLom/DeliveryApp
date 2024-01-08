package backend_group_5.we_lead_bootcamp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString(callSuper = true)

public class Product extends BaseModel {
    private String name;
    private BigDecimal price;
    private String description;
    private Category category;
    private Store store;
    private String serial;
}
