package backend_group_5.we_lead_bootcamp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name="ADDRESS")
@SequenceGenerator(name = "idGenerator", sequenceName = "ADDRESS_SEQ", initialValue = 1, allocationSize = 1)

public class Address extends BaseModel  {
    private String address;
    private Integer streetNumber;
    private String city;
}

