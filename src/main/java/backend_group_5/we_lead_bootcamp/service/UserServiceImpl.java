package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.User;
import backend_group_5.we_lead_bootcamp.repository.BaseRepository;
import backend_group_5.we_lead_bootcamp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    private final UserRepository userRepository;
    @Override
    protected BaseRepository<User, Long> getRepository() {
        return userRepository;
    }
    @Override
    public User createAccount(final User user){
        //User user = new User();
        user.setEmail(user.getEmail());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
       // user.setAppUserRole(Role.USER);
      //  user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.createAccount(user);
        return user;
    }
    @Override
    public void deleteAccount(final User  user){

    }

    @Override
    public User findByEmail(String email) {
        return null;
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

    @Override
    public List<User> createAll(User... items) {
        return null;
    }
}
