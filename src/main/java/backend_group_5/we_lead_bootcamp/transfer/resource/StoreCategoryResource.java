package backend_group_5.we_lead_bootcamp.transfer.resource;

import backend_group_5.we_lead_bootcamp.model.enums.StoreCategoryVariation;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class StoreCategoryResource extends BaseResource{
    @NotNull(message = "Description is required")
    private String description;
    @NotNull
    private StoreCategoryVariation category;

}
