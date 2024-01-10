package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Role;
import backend_group_5.we_lead_bootcamp.model.User;
import backend_group_5.we_lead_bootcamp.repository.BaseRepository;
import backend_group_5.we_lead_bootcamp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService  {
    private final UserRepository userRepository;

    @Override
    protected BaseRepository<User, Long> getRepository() {
        return userRepository;
    }
    @Override
    public User createAccount(final User userResource){
        User user = new User();
        user.setEmail(userResource.getEmail());
        user.setFirstName(userResource.getFirstName());
        user.setLastName(userResource.getLastName());
        user.setAppUserRole(Role.USER);
      //user.setPassword(passwordEncoder.encode(userResource.getPassword()));
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
    public boolean logIn(User user) {
        //verify email and password
     /*   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userResource.getUsername(),userResource.getPassword()));
        var user = userRepository.findByEmail(userResource.getEmail());
                //.orElseThrow(()-> new IllegalArgumentException("Invalid username or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken((String) refreshToken);
        return jwtAuthenticationResponse;*/
        return true;
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
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username);
            }
        };
    }
    /*
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.loadUserByUsername(email);
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .roles(roles.toArray(new String[0]))
                        .build();
        return userDetails;
    }*/



}
