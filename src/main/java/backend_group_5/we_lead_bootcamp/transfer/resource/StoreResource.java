package backend_group_5.we_lead_bootcamp.transfer.resource;

import backend_group_5.we_lead_bootcamp.model.StoreCategoryVariation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    //@Pattern(regexp = "\\d{10}", message = "Phone number must be of 10 digits")
    private String phone;
    @NotNull
    private String vatNumber;
    private BigDecimal minOrderAmount;
    @NotNull
    private Integer deliveryTime;
    //private List<Review> reviews;
    private Double rating;


}

