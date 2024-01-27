package backend_group_5.we_lead_bootcamp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
@SequenceGenerator(name = "idGenerator", sequenceName = "STORES_SEQ", allocationSize = 1)
public class Store extends BaseModel {

    @NotBlank(message = "Name field is required")
    private String name;
    @NotBlank(message = "Address field is required")
    private String address;
    @NotNull(message = "Phone number is required")
    @Column(unique = true)
    @Pattern(regexp = "\\d{10}", message = "Phone number must be of 10 digits")
    private String phone; // error for 10digits @Bootstrap, also with String(value.of())
    @NotBlank(message = "VAT Number is required") //string - VAT can be alphanumeric
    @Column(unique = true)
    private String vatNumber;
    @DecimalMin(value = "0.0", inclusive = false, message = "The minimum order amount must be greater than zero")
    @DecimalMax(value = "20.0", message = "The minimum order amount must not exceed 20.0")
    private BigDecimal minOrderAmount;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Category field is required")
    private StoreCategoryVariation category;
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private List<Product> products;
    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Review> reviews;
    @NotNull
    private Integer DeliveryTime; // in minutes

}
