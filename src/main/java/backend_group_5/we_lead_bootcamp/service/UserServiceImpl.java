package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.User;
import backend_group_5.we_lead_bootcamp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    private final UserRepository userRepository;
    private static final SecureRandom RAND = new SecureRandom();
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
    @Override
    protected JpaRepository<User, Long> getRepository() {
        return userRepository;
    }


    public static String generateSalt (final int length) {

        if (length < 1) {
            System.err.println("error in generateSalt: length must be > 0");
            return "";
        }

        byte[] salt = new byte[length];
        RAND.nextBytes(salt);

        return Base64.getEncoder().encodeToString(salt);
        //check for absense
    }


    public static String hashPassword (String password, String salt) {

        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(securePassword);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println("Exception encountered in hashPassword()");
            // You might want to throw an exception or handle the error in a different way
            return ""; // Placeholder return value, handle the error accordingly
        } finally {
            spec.clearPassword();
        }
    }
    public static boolean verifyPassword (String password, String key, String salt) {
        String optEncrypted = hashPassword(password, salt);
        // true h false
        return optEncrypted.equals(key);
    }
    @Override
    public User createAccount(final User new_user){
        User user = new User();
       String salt = generateSalt(KEY_LENGTH);
       String password = new_user.getPassword();
       String encodedPassword = hashPassword(password,salt);

        user.setEmail(new_user.getEmail());

        user.setPassword(encodedPassword);
        user.setFirstName(new_user.getFirstName());
        user.setLastName(new_user.getLastName());

        userRepository.save(user);
//h create method yparxei hdh sto base service alla emeis theloyme na ginettai encryption sto password
        // otan dhmioyrgeitai o xristis opote isos gi ayto na voleyei na einai seperate method
        return user;
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
        //na parei olo to obj, meso setphone px
        var user = userRepository.getById(Id);
        user.setPhone(phone);
        userRepository.save(user);//me save

    }

    @Override
    public void updateEmail(Long Id,String email) {
        var user = userRepository.getById(Id);
        user.setEmail(email);
        userRepository.save(user);
    }

    @Override
    public void updatePassword(String password) {


    }


}
