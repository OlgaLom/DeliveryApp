package backend_group_5.we_lead_bootcamp.transfer.resource;

import backend_group_5.we_lead_bootcamp.model.StoreCategory;
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

    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private StoreCategory category;
    @NotNull
    private String location; // *
    @Pattern(regexp = "\\d{10}", message = "Phone number must be of 10 digits")
    private String phone;
    @NotNull
    private Integer vatNumber;
    private BigDecimal minOrderAmount;

}

