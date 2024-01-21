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
public class Store extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name field is required")
    private String name;
    @NotBlank(message = "Address field is required")
    private String address;
    @NotNull(message = "Phone number is required")
    private Integer phone;
    @NotNull(message = "VAT Number is required")
    private Integer vatNumber;
    @DecimalMin(value = "0.0", inclusive = false, message = "The minimum order amount must be greater than zero")
    private BigDecimal minOrderAmount;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Category field is required")
    private StoreCategory category;

    @OneToMany(mappedBy = "store")
    private List<Product> products;


}
