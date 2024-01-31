package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Address;
import backend_group_5.we_lead_bootcamp.model.Order;
import backend_group_5.we_lead_bootcamp.model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService  extends  BaseService<User,Long>{
    //User createAccount(User user); // returns type User based on lectures (lecture 14 part B)

    User createAccount(User user);
    void deleteAccount(String email);
    User findByEmail(String email);
    User logIn(String user,String password);
    void logOut();

     String hashPassword(String password, String salt) ;
    boolean verifyPassword (String password, String key, String salt);
    String generateSalt (final int length);
     Integer setAge(LocalDate birthDate);

    void updatePhone(String email, String phone);
    void updateEmail(String email,String new_email,String password);
    void updatePassword(String email,String password,String new_password);

    void updateAddress(Long Id, Address address);
    //rate store

    //add favourite store
    void updateFavouriteStores(User user, String name);
    //leave a comment

    //get order history

    List<Order> getOrderHistory(Long userId);

    List<Address> getUserAddressList(Long userId);
;
}
