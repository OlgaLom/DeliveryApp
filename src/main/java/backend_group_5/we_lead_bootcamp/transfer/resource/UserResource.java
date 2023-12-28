package backend_group_5.we_lead_bootcamp.transfer.resource;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString(callSuper = true)
public class UserResource extends BaseResource{
    //No validations yet
    private long id;
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


}
