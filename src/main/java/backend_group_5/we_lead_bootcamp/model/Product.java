package backend_group_5.we_lead_bootcamp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

public class Product extends BaseModel {
    private String name;
    private BigDecimal price;
    private String description;
    private ProductCategory productCategory;
    @ManyToOne
    @JoinColumn(name = "store") //adjust column name based on DB schema
    private Store store;
    private String serial;
    private List<Variation> variations;
}
