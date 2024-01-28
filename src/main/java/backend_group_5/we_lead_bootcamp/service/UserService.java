package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Address;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.User;

public interface UserService  extends  BaseService<User,Long>{
    //User createAccount(User user); // returns type User based on lectures (lecture 14 part B)

    User createAccount(User user);
    void deleteAccount(String email);
    User findByEmail(String email);
    User logIn(String user,String password);
    void logOut();



    void updatePhone(String email, String phone);
    void updateEmail(String email,String new_email,String password);
    void updatePassword(String email,String password,String new_password);

    void updateAddress(Long Id, Address address);
    //rate store

    //add favourite store
    User updateFavouriteStores(Long userId, Store store);
    //leave a comment
    //get order history
}
