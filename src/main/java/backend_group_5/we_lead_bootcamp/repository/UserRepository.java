package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User createAccount(User user);
    void deleteAccount(User user);
    User findByEmail(String email);
    void logIn(User user);
    void logOut();
    User updatePhone(Integer phone);
    User updateEmail(String email);
    User updatePassword(String password);
}
