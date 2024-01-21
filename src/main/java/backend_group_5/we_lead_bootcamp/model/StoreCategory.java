package backend_group_5.we_lead_bootcamp.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class StoreCategory extends BaseModel {
    private String description;
    private String name;
}
