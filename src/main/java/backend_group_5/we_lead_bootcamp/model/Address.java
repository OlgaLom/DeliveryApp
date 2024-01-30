package backend_group_5.we_lead_bootcamp.model;


import jakarta.persistence.*;
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
@ToString(callSuper = true,exclude = "user")
@Entity
@Table(name="ADDRESS")
@SequenceGenerator(name = "idGenerator", sequenceName = "ADDRESS_SEQ", initialValue = 1, allocationSize = 1)
public class Address extends BaseModel  {

    @Column
    private String address;
    @Column
    private Integer streetNumber;
    @Column
    private String city;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;





}

