package backend_group_5.we_lead_bootcamp.transfer.resource;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class StoreCategoryResource {
    @NotNull(message = "Description is required")
    private String description;
}
