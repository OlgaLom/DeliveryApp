package backend_group_5.we_lead_bootcamp.model;

import backend_group_5.we_lead_bootcamp.model.enums.PaymentMethod;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
//add colums to all
@Table(name = "USERS", indexes = {@Index(columnList = "email")})
@SequenceGenerator(name = "idGenerator", sequenceName = "USER_SEQ", initialValue = 1, allocationSize = 1)
public class User extends  BaseModel{

    @NotNull(message = "Email address cannot be null")
    @Email
    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column
    private String password;

    //@NotNull(message = "First name cannot be null")
    @Column
    private String firstName;
    //@NotNull(message = "Last name cannot be null")
    @Column
    private  String lastName;
    @Min(value = 18, message = "A customer cannot be under 18")
    @Max(value = 120, message = "A customer cannot be above 18")
    @Column
    private Integer age;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn  // Define the foreign key column
    private List<Address> addressList;

    @Column
    private Integer phone;
    //private Address addressObj;se List Sto Address class perilamvanetai to address, streetNumber kai city san idea to vazo to sizitame
    @Column
    private  String city;
    @Enumerated(EnumType.STRING)
    @Column
    private PaymentMethod paymentMethod;
    @Enumerated(value = EnumType.STRING)
    @Column
    private Role role;

}