package backend_group_5.we_lead_bootcamp.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString(callSuper = true)
public class Store extends BaseModel {

    private long id;

    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Address is required")
    private String address;
    @NotNull(message = "Phone is required")
    private Integer phone;
    @NotNull(message = "VAT Number is required")
    private Integer vatNumber;
    //least order amount
    @NotNull(message = "Category is required")
    private StoreCategory category;

    private Product product;


}
