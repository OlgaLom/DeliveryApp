package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.User;
import backend_group_5.we_lead_bootcamp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private  UserRepository userRepository;






 /*   @Override
    protected BaseRepository<User, Long> getRepository() {
        return userRepository;
    }*/
    @Override
    public User createAccount(final User new_user){

     //   String encodedPassword = bCryptPasswordEncoder.encode(new_user.getPassword());
        new_user.setEmail(new_user.getEmail());
    //    new_user.setUsername(new_user.getEmail());
     //  new_user.setPassword(encodedPassword);
   userRepository.createAccount(new_user);

       return new_user;
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
    public User logIn(String username) {

        //verify email and password

       var user_login = userRepository.findByEmail(username);

        return user_login;
    }

    @Override
    public void logOut() {

    }

    @Override
    public void updatePhone(Long Id,Integer phone) {
        var user = userRepository.getById(Id);
        user.setPhone(phone);
        userRepository.updatePhone(Id,phone);

    }

    @Override
    public void updateEmail(Long Id,String email) {
        userRepository.updateEmail(Id,email);
    }

    @Override
    public void updatePassword(String password) {
        userRepository.updatePassword(password);
    }

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    /*  @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    };*/

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
