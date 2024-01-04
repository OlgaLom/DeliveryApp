package backend_group_5.we_lead_bootcamp.service;
import  backend_group_5.we_lead_bootcamp.model.User;
import org.mapstruct.control.MappingControl;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService  extends  BaseService<User,Long>{
    User createAccount(User user); // returns type User based on lectures (lecture 14 part B)
    void deleteAccount(User user);
    User findByEmail(String email);
    void logIn(User user);
    void logOut();
    User updatePhone(Integer phone);
    User updateEmail(String email);
    User updatePassword(String password);

}
