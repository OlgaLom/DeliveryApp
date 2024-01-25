package backend_group_5.we_lead_bootcamp.transfer.resource;

import backend_group_5.we_lead_bootcamp.model.StoreCategoryVariation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
public class StoreResource extends BaseResource {

    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotNull
    private StoreCategoryVariation category;
    @NotNull
    private String location;
    @Pattern(regexp = "\\d{10}", message = "Phone number must be of 10 digits")
    private Integer phone;
    @NotNull
    private Integer vatNumber;
    private BigDecimal minOrderAmount;

}

