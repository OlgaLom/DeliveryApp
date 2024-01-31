package backend_group_5.we_lead_bootcamp.model;

import backend_group_5.we_lead_bootcamp.model.enums.PaymentMethod;
import backend_group_5.we_lead_bootcamp.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "USERS", indexes = {@Index(columnList = "email")})
@SequenceGenerator(name = "idGenerator", sequenceName = "USER_SEQ", initialValue = 1, allocationSize = 1)
public class User extends  BaseModel{

    @NotNull(message = "Email address cannot be null")
    @Email
    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column
    @NotNull
    private String password;

    //@NotNull(message = "First name cannot be null")
    @Column
    private String firstName;
    //@NotNull(message = "Last name cannot be null")
    @Column
    private  String lastName;
    @Temporal(TemporalType.DATE)
    @NotNull
    @Column
    private LocalDate birthDate; //dd/MM/yyyy
    @Min(value = 18, message = "A customer cannot be under 18")
    @Max(value = 120, message = "A customer cannot be above 120")
    @Column
    private Integer age;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private List<Address> addressList;
    @Size(min = 10, max = 10, message = "Phone number must be of 10 digits")
    @Column(unique = true)
    @NotNull
    private String phone;
    @Column
    private  String city;
    @Enumerated(value = EnumType.STRING)
    @Column
    private PaymentMethod paymentMethod;
    @Enumerated(value = EnumType.STRING)
    @Column
    private Role role;
    @Column
    private String storedSalt;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private List<Store> favouriteStores;


}