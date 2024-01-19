package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.User;


public interface UserRepository extends BaseRepository<User,Long> {
    User createAccount(User user);
    void deleteAccount(User user);
    User findByEmail(String email);
    User logIn(User user);
    void logOut();
    void updatePhone(Long Id,Integer phone);
    void updateEmail(Long Id,String email);
    void updatePassword(String password);


 //   Optional<Object> findByUsername(String username);
}
