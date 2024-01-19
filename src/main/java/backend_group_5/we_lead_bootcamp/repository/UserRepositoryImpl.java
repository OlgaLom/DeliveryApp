package backend_group_5.we_lead_bootcamp.repository;
import backend_group_5.we_lead_bootcamp.model.User;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository{
    private final ConcurrentHashMap<Long, User> storage = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);
   @Override
    protected ConcurrentHashMap<Long, User> getStorage() {
        return storage;
    }

    @Override
    protected AtomicLong getSequence() {

        return sequence;
    }


    @Override

    public User createAccount(User user) {
       // save(user);
        create(user);
        return user;
        //den kanei save sto database
    }

    @Override
    public void deleteAccount(User user) {

    }

    @Override
    public User findByEmail(String email) {
        return storage.values()
                .stream()
                .filter(c -> email.equalsIgnoreCase(c.getEmail()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User logIn(User user) {
        String email = user.getEmail();
        return findByEmail(email);
    }

    @Override
    public void logOut() {

    }

    @Override
    public void updatePhone(Long Id,Integer phone) {
        User loggedInUser = storage.values()
                .stream()
                .filter(c -> Id.equals(c.getId()))
                .findFirst()
                .orElse(null);
        loggedInUser.setPhone(phone);

    }

    @Override
    public void updateEmail(Long Id,String email) {
        // updateEmail(email);
        User loggedInUser = storage.values()
                .stream()
                .filter(c -> Id.equals(c.getId()))
                .findFirst()
                .orElse(null);
        loggedInUser.setEmail(email);
    }

    @Override
    public void updatePassword(String password) {
        //   updatePassword(password);
    }


    @Override
    public User getById(Long aLong) {
        return null;
    }




    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(User entity) {

    }








}
