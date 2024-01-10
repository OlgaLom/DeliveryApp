package backend_group_5.we_lead_bootcamp.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "_user")
public class User extends  BaseModel implements UserDetails {
    @Id
    //annotation for unique identifier of user class
   @GeneratedValue
    @Column(name="_user_id")
    //gia na to kanei generate mono toy to Id, default strategy auto
    private Long id;
    private String email;
    private Integer phone;
    private String username;
    private String password;
    // mporei na thelei get gia username kai password apto security
    private Integer age;
    private String address;
    //private Address addressObj; Sto Address class perilamvanetai to address, streetNumber kai city san idea to vazo to sizitame
    private String firstName;
    private  String lastName;
    private  String city;
   // @Enumerated(EnumType.STRING)
    private Role appUserRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(appUserRole.name()));
    }
    @Override
    public  String getUsername(){
        return email; //ayto xreiazetai logo toy UserDetails
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
