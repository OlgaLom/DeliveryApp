package backend_group_5.we_lead_bootcamp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name="ORDER_ADDRESS")
@SequenceGenerator(name = "idGenerator", sequenceName = "ORDER_ADDRESS_SEQ", initialValue = 1, allocationSize = 1)
public class OrderAddress extends BaseModel{
    @Column(length = 30 , nullable = false)
    private String address;
    @Column(nullable = false)
    private Integer streetNumber;
    @Column(length = 50,nullable = false)
    private String city;

}
