package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.User;
import backend_group_5.we_lead_bootcamp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    private final UserRepository userRepository;
    @Override
    protected JpaRepository<User, Long> getRepository() {
        return userRepository;
    }
    @Override
    public User createAccount(final User new_user){
        //   String encodedPassword = bCryptPasswordEncoder.encode(new_user.getPassword());
        new_user.setEmail(new_user.getEmail());
        //    new_user.setUsername(new_user.getEmail());
        //  new_user.setPassword(encodedPassword);
        userRepository.save(new_user);

        return new_user;
    }
    @Override
    public void deleteAccount(final User  user){

    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User logIn(String user) {
        var user_login = userRepository.findByEmail(user);

        return user_login;
    }

    @Override
    public void logOut() {

    }

    @Override
    public void updatePhone(Long Id,Integer phone) {
        var user = userRepository.getById(Id);
        user.setPhone(phone);
        userRepository.updatePhone(Id,phone);

    }

    @Override
    public void updateEmail(Long Id,String email) {
        userRepository.updateEmail(Id,email);
    }

    @Override
    public void updatePassword(String password) {
        userRepository.updatePassword(password);
    }

    @Override
    public List<User> createAll(User... items) {
        return null;
    }
}
