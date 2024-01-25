package backend_group_5.we_lead_bootcamp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name="PRODUCTS",indexes = {@Index(columnList = "serial")})
@SequenceGenerator(name = "idGenerator",sequenceName = "PRODUCTS_SEQ",initialValue = 1,allocationSize = 1)
public class Product extends BaseModel {

    //name
    @NotNull
    @Column(length = 50,nullable = false)
    private String name;

    //price
    @NotNull
    @DecimalMin(value = "0.0",inclusive = false,message = "The minimum price must be greater than zero")
    @Column(precision = 10,scale = 2,nullable = false)
    private BigDecimal price;

    //description
    @NotNull
    @Column(length = 50,nullable = false)
    private String description;

    //product Category
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private ProductCategory productCategory;

    //Store
    @ManyToOne
    @JoinColumn(name = "STORE_ID") //adjust column name based on DB schema
    private Store store;

    //serial
    @NotNull
    @Column(length = 30,nullable = false,unique = true)
    private String serial;

    //Variation sizes
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private ProductVariations.Sizes sizes;

    //Variation flavours
    @Enumerated(EnumType.STRING)
    @Column(length = 20,nullable = false)
    private ProductVariations.Flavours flavours;

    //Variation sauces
    @Enumerated(EnumType.STRING)
    @Column(length = 20,nullable = false)
    private ProductVariations.Sauces sauces;

    //Variation toppings
    @Enumerated(EnumType.STRING)
    @Column(length = 20,nullable = false)
    private ProductVariations.Toppings toppings;
}
