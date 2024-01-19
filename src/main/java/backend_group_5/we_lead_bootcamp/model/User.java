package backend_group_5.we_lead_bootcamp.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
//@Entity
//@Table(name = "USERS", indexes = {@Index(columnList = "email")})
//@SequenceGenerator(name = "idGenerator", sequenceName = "USER_SEQ", initialValue = 1, allocationSize = 1)
public class User extends  BaseModel{
  //  @Id
    private Long Id;
    @NotNull(message = "Email address cannot be null")
    //@Email
    //@Column(length = 50, nullable = false, unique = true)
    private String email;
    private Integer phone;

    private String password;

    //@Min(value = 18, message = "A customer cannot be under 18")
    //@Max(value = 120, message = "A customer cannot be above 18")
    private Integer age;
    private String address;
    //private Address addressObj; Sto Address class perilamvanetai to address, streetNumber kai city san idea to vazo to sizitame
    @NotNull(message = "First name cannot be null")
    //@Column(length = 20, nullable = false)
    private String firstName;
    @NotNull(message = "Last name cannot be null")
    //@Column(length = 30, nullable = false)
    private  String lastName;
    private  String city;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
   @Enumerated(EnumType.STRING)
  // @Column(length = 10, nullable = false)
    private Role role;

}
