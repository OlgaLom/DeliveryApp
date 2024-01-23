package backend_group_5.we_lead_bootcamp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ProductCategory")
@SequenceGenerator(name = "idGenerator", sequenceName = "PRODUCT_CATEGORY_SEQ", initialValue = 1, allocationSize = 1)
public class ProductCategory extends BaseModel {
    @NotNull
    @Column(length = 50,nullable = false)
    private String description;
    @NotNull
    @Column(length = 50,nullable = false)
    private String name;
}
