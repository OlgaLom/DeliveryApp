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
        // Get all customers

        orderService.findAll().forEach(ord -> logger.info("All Orders → {}", ord));

        // We don't mind if a "find" method returns a null
        logger.info("Does customer exist? {}.", (userService.findByEmail("jpn.doe@example.com") != null));


//        ~~~~~
//        CREATE NEW USERS
//        ~~~~~
//        List<User> UsersCreated = userService.createAll(
//
//                User.builder().email("jpn.doe@example.com")
//                        .phone("987654321")
//                        .password("strongPass456")
//                        .birthDate(LocalDate.parse("1980-10-20"))
//                        //.address("123 Main Street")
//                        .addressList(Arrays.asList(
//                                Address.builder().address("Main Street").streetNumber(123).city("AnotherCity").build()))
//                        .firstName("John")
//                        .lastName("Doe")
//                        // .city("AnotherCity")
//                        .paymentMethod(PaymentMethod.PAYPAL)
//                        .role(Role.USER)
//                        .build()
//        );
//        logger.info("New Users→ {}.", UsersCreated);


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
                    .birthDate(LocalDate.parse("1950-10-20"))
                    .storedSalt(salt1)
                    .paymentMethod(PaymentMethod.CREDIT_CARD)
                    .role(Role.USER)
                    .build()
            ,
            User.builder().email("john.doe@example.com")
                    .phone("987654321")
                    .password(userService.hashPassword("strongPass456",salt2))

                    .addressList(Arrays.asList(
                            Address.builder().address("Main Street").streetNumber(123).city("AnotherCity").build()))
                    .firstName("John")
                    .lastName("Doe")
                    .birthDate(LocalDate.parse("1990-03-12"))
                    .storedSalt(salt2)
                    .paymentMethod(PaymentMethod.PAYPAL)
                    .role(Role.USER)
                    .build(),
            User.builder()
                    .email("jane.smith@example.com")
                    .phone("555555555")
                    .password(userService.hashPassword("securePass789",salt3))

                    .addressList(Arrays.asList(
                            Address.builder().address("Oak Avenue").streetNumber(456).city("YetAnotherCity").build()))
                    .firstName("Jane")
                    .lastName("Smith")
                    .birthDate(LocalDate.parse("1970-12-12"))
                    .paymentMethod(PaymentMethod.COD)
                    .storedSalt(salt3)
                    .role(Role.USER)
                    .build(),
            User.builder()
                    .email("alice.jones@example.com")
                    .phone("111223344")
                    .password("password123")


                    .addressList(Arrays.asList(
                            Address.builder().address("Elm Street").streetNumber(789).city("CityX").build()))
                    .firstName("Alice")
                    .lastName("Jones")

                    .paymentMethod(PaymentMethod.CREDIT_CARD)
                    .role(Role.USER)
                    .build());

             /*  User.builder().email("peter.mercury@outlookx.com")
//                        .firstName("Peter").lastName("Mercury")
//                        .address("Freddie Street 28th")
//                        .age(32).build(),
//                User.builder().email("magdalene.ferguson@gmailx.com")
//                        .firstName("Magdalene").lastName("Ferguson")
//                        .address("Jelly Avenue 73")
//                        .age(32).build(),
//                User.builder().email("jones.pirves@gmailx.com")
//                        .firstName("Jones").lastName("Pirves")
//                        .address("3rd Mountain Hike, 3")
//                        .age(32).build(),
//                User.builder().email("michael.anderson@gmailx.com")
//                        .firstName("Michael").lastName("Anderson")
//                        .address("Hollywood Street 63")
//                        .age(32).build(),
//                User.builder().email("yennefer.lawrance@windowslivex.com")
                        .firstName("Yennefer").lastName("Lawrance")
                        .address("Rivia 43")
                        .age(32).build(),
                User.builder().email("mary.ferry@windowslivex.com")
                        .firstName("Mary").lastName("Ferry")
                        .address("Downtown 17, California")
                        .age(32).build());*/


        User newUserRequest = new User();
        newUserRequest.setEmail("mary.ferry@windowslivex.com");
        newUserRequest.setPassword("marypassword");
        newUserRequest.setFirstName("Mary");
        newUserRequest.setLastName("Shelley");
        newUserRequest.setBirthDate(LocalDate.parse("1990-12-19"));
        newUserRequest.setAge(newUserRequest.setAge()); // Calculate age based on birth date

        newUserRequest.setPaymentMethod(PaymentMethod.CREDIT_CARD);
        newUserRequest.setAddressList(Arrays.asList(
                Address.builder().address("place").streetNumber(1).city("coty").build(),
                Address.builder().address("secondplace").streetNumber(2).city("thiscity").build()
                ));
        User new_user = userService.createAccount(newUserRequest);


      logger.info("Created customer via createAccount",new_user);
        //logger.info("Created customer via createAccount",user_alice);
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

    }

}