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
        return null;
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
    public void logIn(User user) {

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
}
