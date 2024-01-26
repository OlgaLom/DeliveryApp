package backend_group_5.we_lead_bootcamp.bootstrap;

import backend_group_5.we_lead_bootcamp.base.BaseComponent;
import backend_group_5.we_lead_bootcamp.model.*;
import backend_group_5.we_lead_bootcamp.model.enums.Flavours;
import backend_group_5.we_lead_bootcamp.model.enums.Sauces;
import backend_group_5.we_lead_bootcamp.model.enums.Sizes;
import backend_group_5.we_lead_bootcamp.model.enums.Toppings;
import backend_group_5.we_lead_bootcamp.service.ProductCategoryService;
import backend_group_5.we_lead_bootcamp.service.ProductService;
import backend_group_5.we_lead_bootcamp.service.StoreService;
import backend_group_5.we_lead_bootcamp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Component
@Profile("generate-catalog-customers")
@RequiredArgsConstructor
public class CatalogCustomerSampleContentCreator extends BaseComponent implements CommandLineRunner {
    private final ProductService productService;
    private final ProductCategoryService categoryService;

    private final StoreService storeService;
   private final UserService customerService;
    @Override
    public void run(String... args) throws Exception {
       ProductCategory newCategory = categoryService.create(ProductCategory.builder().description("Coffee").build());
        logger.info("Created {}.", newCategory);
        Store newStore= storeService.create(Store.builder().build());
        logger.info("Created {}!",newStore);


       List<Product> products = List.of(
                Product.builder().serial("SN1000-0001").name("FREDO ESPRESSO")
                        .price(BigDecimal.valueOf(2.50)).productCategory(newCategory)
                        .description("Hot espresso shaken with ice cubes").store(newStore)
                        .sizes(Sizes.SMALL).build(),
                Product.builder().serial("SN1000-0002").name("HOT CHOCOLATE")
                        .price(BigDecimal.valueOf(3.00)).productCategory(newCategory)
                        .description("Classic Chocolate").store(newStore)
                        .sizes(Sizes.MEDIUM)
                        .flavours(Flavours.STRAWBERRY)
                        .toppings(Toppings.WHIPPED_CREAM).build(),
                Product.builder().serial("SN1100-0001").name("WAFFLE")
                        .price(BigDecimal.valueOf(6.50)).productCategory(newCategory)
                        .description("Waffle with Nutella").store(newStore)
                        .toppings(Toppings.BISCUIT)
                        .toppings(Toppings.WHIPPED_CREAM).build(),
                Product.builder().serial("SN1100-0002").name("GREEK GIROS")
                        .price(BigDecimal.valueOf(1199)).productCategory(newCategory)
                        .description("Hot espresso shaken with ice cubes").store(newStore)
                        .sizes(Sizes.LARGE).sauces(Sauces.TZATZIKI)
                        .sauces(Sauces.KETCHUP).sauces(Sauces.MUSTARD).build());

                //ADD MORE PRODUCTS AS NEEDED

        var productsCreated = productService.createAll(products);
        logger.info("Created {} products.", productsCreated.size());
        productsCreated.stream()
                .sorted(Comparator.comparing(Product::getId))
                .forEach(p -> logger.debug("{}. {}", p.getId(), p));

        List<User> customersCreated = customerService.createAll(
                User.builder()
                        .email("c.giannacoulis@codehub.gr")
                        .phone(1234567890)
                        .password("securePassword123")
                        .age(47)
                      //  .addressList("3583 Tennessee Avenue")
                       .addressList(Arrays.asList(
                               Address.builder().address("Tennessee Avenue").streetNumber(3583).city("SomeCity").build()))
                        .firstName("Constantinos")
                        .lastName("Giannacoulis")
                       // .city("SomeCity")
                        .paymentMethod(PaymentMethod.CREDIT_CARD)
                        .role(Role.USER)
                        .build(),
                User.builder().email("john.doe@example.com")
                .phone(987654321)
                .password("strongPass456")
                .age(30)
                //.address("123 Main Street")
                .addressList(Arrays.asList(
                        Address.builder().address("Main Street").streetNumber(123).city("AnotherCity").build()))
                .firstName("John")
                .lastName("Doe")
               // .city("AnotherCity")
                .paymentMethod(PaymentMethod.PAYPAL)
                .role(Role.ADMIN)
                .build(),
                User.builder()
                        .email("jane.smith@example.com")
                        .phone(555555555)
                        .password("securePass789")
                        .age(25)
                        .addressList(Arrays.asList(
                                Address.builder().address("Oak Avenue").streetNumber(456).city("YetAnotherCity").build()))
                        .firstName("Jane")
                        .lastName("Smith")
                     //   .city("YetAnotherCity")
                        .paymentMethod(PaymentMethod.COD)
                        .role(Role.USER)
                        .build(),
                User.builder()
                        .email("alice.jones@example.com")
                        .phone(111223344)
                        .password("password123")
                        .age(28)
                    //    .address("789 Elm Street")
                        .addressList(Arrays.asList(
                                Address.builder().address("Elm Street").streetNumber(789).city("CityX").build()))
                        .firstName("Alice")
                        .lastName("Jones")
                       // .city("CityX")
                        .paymentMethod(PaymentMethod.CREDIT_CARD)
                        .role(Role.USER)
                        .build());
              /*  User.builder().email("peter.mercury@outlookx.com")
                        .firstName("Peter").lastName("Mercury")
                        .address("Freddie Street 28th")
                        .age(32).build(),
                User.builder().email("magdalene.ferguson@gmailx.com")
                        .firstName("Magdalene").lastName("Ferguson")
                        .address("Jelly Avenue 73")
                        .age(32).build(),
                User.builder().email("jones.pirves@gmailx.com")
                        .firstName("Jones").lastName("Pirves")
                        .address("3rd Mountain Hike, 3")
                        .age(32).build(),
                User.builder().email("michael.anderson@gmailx.com")
                        .firstName("Michael").lastName("Anderson")
                        .address("Hollywood Street 63")
                        .age(32).build(),
                User.builder().email("yennefer.lawrance@windowslivex.com")
                        .firstName("Yennefer").lastName("Lawrance")
                        .address("Rivia 43")
                        .age(32).build(),
                User.builder().email("mary.ferry@windowslivex.com")
                        .firstName("Mary").lastName("Ferry")
                        .address("Downtown 17, California")
                        .age(32).build());*/

        logger.info("Created {} customers.", customersCreated.size());
        customersCreated.stream()
                .sorted(Comparator.comparing(User::getId))
                .forEach(c -> logger.debug("{}. {}", c.getId(), c));
    }
}
