package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  extends  BaseService<User,Long> {
    User createAccount(User user); // returns type User based on lectures (lecture 14 part B)
    void deleteAccount(User user);
    User findByEmail(String email);
    boolean logIn(User user);
    void logOut();
    User updatePhone(Integer phone);
    User updateEmail(String email);
    User updatePassword(String password);


    UserDetails loadUserByUsername(String userEmail);

    UserDetailsService userDetailsService();
}
