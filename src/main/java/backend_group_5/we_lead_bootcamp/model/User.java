package backend_group_5.we_lead_bootcamp.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
@Table(name = "_user")
public class User extends  BaseModel {
    @Id
    //annotation for unique identifier of user class
    @GeneratedValue
    //gia na to kanei generate mono toy to Id, default strategy auto
    private Long id;
    private String email;
    private Integer phone;
    private String username;
    private String password;
    private Integer age;
    private String address;
    //private Address addressObj; Sto Address class perilamvanetai to address, streetNumber kai city san idea to vazo to sizitame
    private String firstName;
    private  String lastName;
    private  String city;
    private AppUserRole appUserRole;
}
