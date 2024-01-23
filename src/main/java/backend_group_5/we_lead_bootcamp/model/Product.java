package backend_group_5.we_lead_bootcamp.model;

import jakarta.persistence.*;
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
    @NotNull
    @Column(length = 50,nullable = false)
    private String name;
    @NotNull
    @Column(precision = 10,scale = 2,nullable = false)
    private BigDecimal price;
    @NotNull
    @Column(length = 50,nullable = false)
    private String description;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private ProductCategory productCategory;
    @ManyToOne
    @JoinColumn(name = "STORE_ID") //adjust column name based on DB schema
    private Store store;
    @NotNull
    @Column(length = 30,nullable = false,unique = true)
    private String serial;
    @NotNull
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, targetEntity = ProductVariations.class)
    private List<ProductVariations> variations;
}
