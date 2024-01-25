package backend_group_5.we_lead_bootcamp.transfer.resource;

import backend_group_5.we_lead_bootcamp.model.PaymentMethod;
import backend_group_5.we_lead_bootcamp.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
  /*  @NotNull(message = "Email cannot be null")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "The email format is not correct")
    private String email;
    @NotNull
    private  String password;
    @NotNull(message = "Firstname cannot be null")
    private String firstName;

    @NotNull(message = "Lastname cannot be null")
    private String lastName;

    @Min(value = 12, message = "A customer's age cannot be under 12")
    @Max(value = 120, message = "A customer's age cannot be above 120")
    private Integer age;

    @Size(max = 50, message = "Address cannot be longer than 50 characters")
    private String address;*/
    @NotNull(message = "Email address cannot be null")
    private String email;

    private String password;

    //@NotNull(message = "First name cannot be null")

    private String firstName;
    //@NotNull(message = "Last name cannot be null")

    private  String lastName;
    @Min(value = 18, message = "A customer cannot be under 18")
    @Max(value = 120, message = "A customer cannot be above 18")

    private Integer age;

    private String address;

    private Integer phone;
    //private Address addressObj;se List Sto Address class perilamvanetai to address, streetNumber kai city san idea to vazo to sizitame

    private  String city;
    @Enumerated(EnumType.STRING)

    private PaymentMethod paymentMethod;
    @Enumerated(value = EnumType.STRING)

    private Role role;

}