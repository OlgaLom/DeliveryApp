package backend_group_5.we_lead_bootcamp.transfer.resource;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class UserResource extends BaseResource{
    //No validations yet
    /*
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
    private Role appUser;
 */
    @NotNull(message = "Email cannot be null")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "The email format is not correct")
    private String email;
    @NotNull
    private  String password;
    @NotNull(message = "Firstname cannot be null")
    private String firstname;

    @NotNull(message = "Lastname cannot be null")
    private String lastname;

    @Min(value = 12, message = "A customer's age cannot be under 12")
    @Max(value = 120, message = "A customer's age cannot be above 120")
    private Integer age;

    @Size(max = 50, message = "Address cannot be longer than 50 characters")
    private String address;

}