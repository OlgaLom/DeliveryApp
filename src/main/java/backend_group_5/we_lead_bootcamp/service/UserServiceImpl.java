package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Address;
import backend_group_5.we_lead_bootcamp.model.Order;
import backend_group_5.we_lead_bootcamp.model.User;
import backend_group_5.we_lead_bootcamp.model.enums.Role;
import backend_group_5.we_lead_bootcamp.repository.AddressRepository;
import backend_group_5.we_lead_bootcamp.repository.OrderRepository;
import backend_group_5.we_lead_bootcamp.repository.StoreRepository;
import backend_group_5.we_lead_bootcamp.repository.UserRepository;
import jakarta.transaction.Transactional;
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
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;
    private static final SecureRandom RAND = new SecureRandom();
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
    @Override
    protected JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Override
    public  String generateSalt (final int length) {

        if (length < 1) {
            System.err.println("error in generateSalt: length must be > 0");
            return "";
        }

        byte[] salt = new byte[length];
        RAND.nextBytes(salt);

        return Base64.getEncoder().encodeToString(salt);
        //check for absense
    }

    @Override
    public  String hashPassword (String password, String salt) {

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
    @Override
    public  boolean verifyPassword (String password, String key, String salt) {
        String optEncrypted = hashPassword(password, salt);
        // true h false
        return optEncrypted.equals(key);
    }

    @Override
    public List<Order> getOrderHistory(Long userId) {
        return orderRepository.findOrdersByUser(userId) ;
    }

    @Override
    public List<Address> getUserAddressList(Long userId) {
        return addressRepository.findAddressesByUserId(userId);
    }

    @Override
    @Transactional
    public User createAccount(User new_user){
        User user = new User();


       String salt = generateSalt(8);
        String password = new_user.getPassword();
       String encodedPassword = hashPassword(password,salt);

        user.setEmail(new_user.getEmail());

        user.setPassword(encodedPassword);
        user.setStoredSalt(salt);
        System.out.println(encodedPassword);
        user.setFirstName(new_user.getFirstName());
        user.setLastName(new_user.getLastName());
        user.setBirthDate(new_user.getBirthDate());
        user.setAge(new_user.setAge());
        user.setAddressList(new_user.getAddressList());



        user.setPaymentMethod(new_user.getPaymentMethod());

        user.setRole(Role.USER);
        userRepository.save(user);
//h create method yparxei hdh sto base service alla emeis theloyme na ginettai encryption sto password
        // otan dhmioyrgeitai o xristis opote isos gi ayto na voleyei na einai seperate method
        return user;
    }
    @Override
    @Transactional
   public List<User> findAll(){
        List<User> users = userRepository.findAll(); // your logic to get users
        for (User user : users) {
            // Accessing the collection to initialize it
            user.getAddressList().size();
        }
        return users;
    }

    @Override
    public void deleteAccount(final String  email){
       // String email = user.getEmail();
        Long Id = userRepository.findByEmail(email).getId();
        deleteById(Id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public User logIn(String user,String password) {
        User result = null;
        User user_login = userRepository.findByEmail(user);
        String key = hashPassword(password, user_login.getPassword());
        String salt = user_login.getStoredSalt();
        if (verifyPassword(password,user_login.getPassword(),salt)) {
            System.out.println("LOGGED IN");

            result = user_login;
        } else {
            System.out.println("WRONG ");

        }


        return result;
    }

    @Override
    public void logOut() {

    }


    @Override
    public void updatePhone(String email,String phone) {
        //na parei olo to obj, meso setphone px
        var user = userRepository.findByEmail(email);
        user.setPhone(phone);
        userRepository.save(user);//me save

    }

    @Override
    public void updateEmail(String email,String new_email,String password) {
        User user;
        user = findByEmail(email);
        if(user ==null){
            System.out.println("USER NOT FOUND");
        }
        assert user != null;
        String key = hashPassword(password, user.getPassword());

        String salt = user.getStoredSalt();
        if (verifyPassword(password, user.getPassword(),salt)) {
            System.out.println("verified ");
                user.setEmail(new_email);
                userRepository.save(user);
        } else {
            System.out.println("WRONG password or email");

        }

    }

    @Override
    public void updatePassword(String email,String password,String new_password) {
        User user;
        user = findByEmail(email);
        if(user ==null){
            System.out.println("USER NOT FOUND");
        }
        assert user != null;
        String key = hashPassword(password, user.getPassword());
        String salt = user.getStoredSalt();
        if (verifyPassword(password,  user.getPassword(),salt)) {
            System.out.println("verified enter new password");
            String new_salt = generateSalt(8);

            String encodedPassword = hashPassword(new_password,new_salt);
            user.setPassword(encodedPassword);
            user.setStoredSalt(new_salt);
            userRepository.save(user);
        } else {
            System.out.println("WRONG password or email");

        }

    }
    @Override
    public void updateAddress(Long Id, Address address){
        var user = userRepository.getReferenceById(Id);
        List<Address> addressList = user.getAddressList();
       addressList.add(address);
        //user.setAddressList(address);
        addressRepository.save(address);
        userRepository.save(user);
    }



    @Override
    @Transactional
    public void updateFavouriteStores(User user, String name) {

        System.out.println(user);

       storeRepository.getStoreByName(name);


    }
    //add favourite store


}
