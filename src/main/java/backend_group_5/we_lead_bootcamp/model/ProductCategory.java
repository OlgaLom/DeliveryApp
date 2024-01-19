package backend_group_5.we_lead_bootcamp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString(callSuper = true)
public class ProductCategory extends BaseModel {
    private String description;
    private String name;
}
