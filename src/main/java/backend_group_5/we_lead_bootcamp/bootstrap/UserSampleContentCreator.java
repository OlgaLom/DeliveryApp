package backend_group_5.we_lead_bootcamp.bootstrap;

import backend_group_5.we_lead_bootcamp.base.BaseComponent;
import backend_group_5.we_lead_bootcamp.model.Address;
import backend_group_5.we_lead_bootcamp.model.User;
import backend_group_5.we_lead_bootcamp.model.enums.PaymentMethod;
import backend_group_5.we_lead_bootcamp.model.enums.Role;
import backend_group_5.we_lead_bootcamp.service.OrderService;
import backend_group_5.we_lead_bootcamp.service.ProductService;
import backend_group_5.we_lead_bootcamp.service.StoreService;
import backend_group_5.we_lead_bootcamp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Component
@Profile("generate-users")
@RequiredArgsConstructor
public class UserSampleContentCreator extends BaseComponent implements CommandLineRunner {
    private final UserService userService;
    private final StoreService storeService;
    private final OrderService orderService;
    private final ProductService productService;

    @Override
    public void run(String... args) {


        orderService.findAll().forEach(ord -> logger.info("All Orders → {}", ord));

        // We don't mind if a "find" method returns a null
        logger.info("Does customer exist? {}.", (userService.findByEmail("jpn.doe@example.com") != null));


//        ~~~~~
//        CREATE NEW USERS
//        ~~~~~



    String salt1 = userService.generateSalt(8);
    String salt2 = userService.generateSalt(8);
        String salt3 = userService.generateSalt(8);
        String salt4 = userService.generateSalt(8);
    List<User> customersCreated = userService.createAll(

            User.builder()

                    .email("c.giannacoulis@codehub.gr")
                    .phone("1234567890")
                    .password(userService.hashPassword("securePassword123",salt1))
                    .addressList(Arrays.asList(
                            Address.builder().address("Tennessee Avenue").streetNumber(3583).city("SomeCity").build()))
                    .firstName("Constantinos")
                    .lastName("Giannacoulis")
                    .age(userService.setAge(LocalDate.parse("1950-10-20")))
                    .birthDate(LocalDate.parse("1950-10-20"))
                    .storedSalt(salt1)
                    .paymentMethod(PaymentMethod.CREDIT_CARD)
                    .role(Role.USER)
                    .build()
            ,
            User.builder().email("john.doe@example.com")
                    .phone("9876543210")
                    .password(userService.hashPassword("strongPass456",salt2))
                    .addressList(Arrays.asList(
                            Address.builder().address("Main Street").streetNumber(123).city("AnotherCity").build()))
                    .firstName("John")
                    .lastName("Doe")
                    .birthDate(LocalDate.parse("1990-03-12"))
                    .age(userService.setAge(LocalDate.parse("1990-03-12")))
                    .storedSalt(salt2)
                    .paymentMethod(PaymentMethod.PAYPAL)
                    .role(Role.USER)
                    .build(),
            User.builder()
                    .email("jane.smith@example.com")
                    .phone("5555555559")
                    .password(userService.hashPassword("securePass789",salt3))

                    .addressList(Arrays.asList(
                            Address.builder().address("Oak Avenue").streetNumber(456).city("YetAnotherCity").build()))
                    .firstName("Jane")
                    .lastName("Smith")
                    .birthDate(LocalDate.parse("1970-12-12"))
                    .age(userService.setAge(LocalDate.parse("1970-12-12")))
                    .paymentMethod(PaymentMethod.COD)
                    .storedSalt(salt3)
                    .role(Role.USER)
                    .build(),
            User.builder()
                    .email("alice.jones@example.com")
                    .phone("1112233446")
                    .password(userService.hashPassword("password123",salt4))
                    .birthDate(LocalDate.parse("2000-02-18"))
                    .age(userService.setAge(LocalDate.parse("2000-02-18")))
                    .storedSalt(salt4)
                    .addressList(Arrays.asList(
                            Address.builder().address("Elm Street").streetNumber(789).city("CityX").build()))
                    .firstName("Alice")
                    .lastName("Jones")

                    .paymentMethod(PaymentMethod.CREDIT_CARD)
                    .role(Role.USER)
                    .build());




        User newUserRequest = new User();
        newUserRequest.setEmail("mary.ferry@windowslivex.com");
        newUserRequest.setPassword("marypassword");
        newUserRequest.setFirstName("Mary");
        newUserRequest.setLastName("Shelley");
        newUserRequest.setPhone("3456781234");
        newUserRequest.setBirthDate(LocalDate.parse("1990-12-19"));
        // Calculate age based on birth date
        newUserRequest.setPaymentMethod(PaymentMethod.COD);
        newUserRequest.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        newUserRequest.setAddressList(Arrays.asList(
                Address.builder().address("place").streetNumber(1).city("coty").build(),
                Address.builder().address("secondplace").streetNumber(2).city("thiscity").build()
                ));
        User new_user = userService.createAccount(newUserRequest);


      logger.info("Created customer via createAccount",new_user);

        logger.info("Created {} customers.", customersCreated.size());
                customersCreated.stream()
                .sorted(Comparator.comparing(User::getId))
                .forEach(c -> logger.debug("{}. {}", c.getId(), c));
        ////////////////////////////////////////////////////////
        /////TEST LOGIN AND PASSWORD VERIFICATION//////////////


        User logged_in_user = userService.logIn(new_user.getEmail(),newUserRequest.getPassword());
        //when logging user writes their password
       logger.info("Logged in user is:",logged_in_user);
       ///////////////////////////////////////////////////////
        ///////////TEST UPDATE PHONE/////////////
        userService.updatePhone(customersCreated.get(0).getEmail(),"9134567903");

        logger.info("show user order history",userService.getOrderHistory(new_user.getId()));


    }

}
