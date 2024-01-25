package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Address;
import backend_group_5.we_lead_bootcamp.model.User;

public interface UserService  extends  BaseService<User,Long>{
    //User createAccount(User user); // returns type User based on lectures (lecture 14 part B)

    User createAccount(User user);
    void deleteAccount(User user);
    User findByEmail(String email);
    User logIn(String user);
    void logOut();
    void updatePhone(Long Id,Integer phone);
    void updateEmail(Long Id,String email);
    void updatePassword(String password);

    void updateAddress(Long Id, Address address);
}
