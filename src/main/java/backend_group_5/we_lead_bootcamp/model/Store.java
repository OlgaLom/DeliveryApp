package backend_group_5.we_lead_bootcamp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="STORES")
@SequenceGenerator(name = "idGenerator", sequenceName = "ORDERS_SEQ", initialValue = 1, allocationSize = 1)
public class Store extends BaseModel {

    @NotBlank(message = "Name field is required")
    private String name;
    @NotBlank(message = "Address field is required")
    private String address;
    @NotNull(message = "Phone number is required")
    private Integer phone;
    @NotBlank(message = "VAT Number is required")
    private String vatNumber;
    @DecimalMin(value = "0.0", inclusive = false, message = "The minimum order amount must be greater than zero")
    private BigDecimal minOrderAmount;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Category field is required")
    private StoreCategoryVariation category;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Product> products;
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @NotNull private Integer DeliveryTime; // in minutes
    private double Rating;
    @NotNull
    public double AverageRating;
    @NotBlank(message = "Opening Hours field is required")
    private String openingHours;

}
