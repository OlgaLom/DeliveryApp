package backend_group_5.we_lead_bootcamp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STORE_CATEGORIES")
@SequenceGenerator(name = "idGenerator", sequenceName = "STORE_CATEGORIES_SEQ", initialValue = 1, allocationSize = 40)
@ToString(callSuper = true)
public class StoreCategory extends BaseModel {


    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Name field is required")
    private StoreCategoryVariation displayName;
}
