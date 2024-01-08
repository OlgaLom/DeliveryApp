package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Role;
import backend_group_5.we_lead_bootcamp.model.User;
import backend_group_5.we_lead_bootcamp.repository.BaseRepository;
import backend_group_5.we_lead_bootcamp.repository.UserRepository;
import backend_group_5.we_lead_bootcamp.transfer.JwtAuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    @Override
    protected BaseRepository<User, Long> getRepository() {
        return userRepository;
    }
    @Override
    public User createAccount(final User user){
        //User user = new User();
        user.setEmail(user.getEmail());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setAppUserRole(Role.USER);
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       userRepository.createAccount(user);
       return user;
    }
    @Override
    public void deleteAccount(final User  user){

    }

    @Override
    public User findByEmail(String email) {

        return userRepository.findByEmail(email);
        //UsernameNotFoundException
    }

    @Override
    public JwtAuthenticationResponse logIn(User user) {
        //verify email and password
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        var user1 = userRepository.findByEmail(user.getEmail());
                //.orElseThrow(()-> new IllegalArgumentException("Invalid username or password"));
        var jwt = jwtService.generateToken(user1);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user1);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken((String) refreshToken);
        return jwtAuthenticationResponse;
    }

    @Override
    public void logOut() {

    }

    @Override
    public User updatePhone(Integer phone) {
        return null;
    }

    @Override
    public User updateEmail(String email) {
        return null;
    }

    @Override
    public User updatePassword(String password) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) {
        return null;
    }
    @Override
    public UserDetailsService userDetailsService(){
        return  new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username);
            }
        };
    }



}
