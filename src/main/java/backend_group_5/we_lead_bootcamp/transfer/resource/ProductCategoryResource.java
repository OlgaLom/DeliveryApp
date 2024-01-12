package backend_group_5.we_lead_bootcamp.transfer.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@ToString(callSuper = true)
public class ProductCategoryResource extends BaseResource{
    @NotNull(message="Description cannot be null")
    private String description;
}
