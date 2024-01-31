package backend_group_5.we_lead_bootcamp.transfer.resource;

import backend_group_5.we_lead_bootcamp.model.enums.PaymentMethod;
import backend_group_5.we_lead_bootcamp.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
public class UserResource extends BaseResource{

    @NotNull(message = "Email address cannot be null")
    private String email;

   @NotNull
    private String password;

    //@NotNull(message = "First name cannot be null")

    private String firstName;
    //@NotNull(message = "Last name cannot be null")

    private  String lastName;
    //@JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonFormat(pattern="yyyy/MM/dd")
    @NotNull
    private LocalDate birthDate;
    @Min(value = 18, message = "A customer cannot be under 18")
    @Max(value = 120, message = "A customer cannot be above 120")
    private Integer age;

    private List<AddressResource> addressList;
    @NotNull
    private String phone;

    private  String city;

    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(value = EnumType.STRING)
    private Role role;

}