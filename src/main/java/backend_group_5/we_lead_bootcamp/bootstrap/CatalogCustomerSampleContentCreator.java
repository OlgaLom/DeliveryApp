package backend_group_5.we_lead_bootcamp.bootstrap;

import backend_group_5.we_lead_bootcamp.base.BaseComponent;
import backend_group_5.we_lead_bootcamp.model.Product;
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

@Component
@Profile("generate-catalog-customers")
@RequiredArgsConstructor
public class CatalogCustomerSampleContentCreator extends BaseComponent implements CommandLineRunner {
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;

    private final StoreService storeService;
    private final UserService customerService;
    @Override
    public void run(String... args) throws Exception {
        //create productCategory
//       ProductCategory newCategory = productCategoryService.create(ProductCategory.builder()
//               .description("Coffee")
//               .name("Coffee House").build());
//        logger.info("Created {}.", newCategory);

//        ProductCategory newCategory2=productCategoryService.create(ProductCategory.builder()
//                .description("Pizza")
//                .name("Pizzas")
//                .build());
//        logger.info("Created {}",newCategory2);
//        ProductCategory newCategory3=productCategoryService.create(ProductCategory.builder()
//                .description("Souvlaki,Giros,Steak")
//                .name("Steakhouse")
//                .build());
//        logger.info("Created {}",newCategory3);

//        //Create Store
//        Store newStore = storeService.createStore(Store.builder()
//                .name("Store1")
//                .address("Address 1")
//                .phone(String.valueOf("2310123453"))
//                .vatNumber("VAT123")
//                .minOrderAmount(BigDecimal.valueOf(5))
//                .DeliveryTime(30).products(new ArrayList<>())
//                .category(StoreCategoryVariation.CAFE)
//                .build());
//        logger.info("Created {}!",newStore);

//        Store newStore2 = storeService.createStore(Store.builder()
//                .name("Semeli")
//                .address("Address 3")
//                .phone(String.valueOf("2310123453"))
//                .vatNumber("VAT124")
//                .minOrderAmount(BigDecimal.valueOf(5))
//                .DeliveryTime(30).products(new ArrayList<>())
//                .category(StoreCategoryVariation.PIZZERIA)
//                .build());
//        logger.info("Created {}!",newStore2);

//        Store newStore3 = storeService.createStore(Store.builder()
//                .name("Babis")
//                .address("Address 15")
//                .phone(String.valueOf("2310123454"))
//                .vatNumber("VAT128")
//                .minOrderAmount(BigDecimal.valueOf(5))
//                .DeliveryTime(30).products(new ArrayList<>())
//                .category(StoreCategoryVariation.STEAKHOUSE)
//                .build());
//        logger.info("Created {}!",newStore3);

    //Create Product
//        Product newProduct=productService.create(Product.builder()
//                .name("Fredo Espresso")
//                .price(BigDecimal.valueOf(4.30))
//                .description("Hot espresso shaken with ice cubes")
//                .productCategory(productCategoryService.getById(17L))
//                .store(storeService.getStoreByName("Coffee House"))
//                .serial("SN1000-0001")
//                .sizes(Sizes.MEDIUM)
//                .flavours(Flavours.HAZELNUT)
//                .sauces(Sauces.TZATZIKI)
//                .toppings(Toppings.WHITE_SUGAR)
//                .build());
//        logger.info("Created {}!",newProduct);
        Product newProduct=productService.create(Product.builder()
                .name("Fredo Espresso")
                .price(BigDecimal.valueOf(4.30))
                .description("Hot espresso shaken with ice cubes")
                .productCategory(productCategoryService.getById(1L))
                .store(storeService.getStoreByName("Helga Store"))
                .serial("SN1000-0016")
                .sizes(Sizes.NONE)
                .flavours(Flavours.NONE)
                .sauces(Sauces.NONE)
                .toppings(Toppings.NONE)
                .build());
        logger.info("Created {}!",newProduct);


//       List<Product> products = List.of(
//                Product.builder().serial("SN1000-0002").name("HOT CHOCOLATE")
//                        .price(BigDecimal.valueOf(3.00)).productCategory(productCategoryService.getById(17L))
//                        .description("Classic Chocolate").store(storeService.getStoreByName("Coffee House"))
//                        .sizes(Sizes.MEDIUM)
//                        .flavours(Flavours.STRAWBERRY)
//                        .toppings(Toppings.WHIPPED_CREAM)
//                        .sauces(Sauces.NONE)
//                        .build(),
//                Product.builder().serial("SN1100-0001").name("WAFFLE")
//                        .price(BigDecimal.valueOf(6.50)).productCategory(productCategoryService.getById(17L))
//                        .description("Waffle with Nutella").store(storeService.getStoreByName("Coffee House"))
//                        .toppings(Toppings.BISCUIT)
//                        .toppings(Toppings.WHIPPED_CREAM).build(),
//                Product.builder().serial("SN1100-0002").name("GREEK GIROS")
//                        .price(BigDecimal.valueOf(1199)).productCategory(newCategory)
//                        .description("Hot espresso shaken with ice cubes").store(newStore2)
//                        .sizes(Sizes.LARGE).sauces(Sauces.TZATZIKI)
//                        .sauces(Sauces.KETCHUP).sauces(Sauces.MUSTARD).build()
//                Product.builder().serial("SN1100-0003").name("Pizza")
//                        .price(BigDecimal.valueOf(15.00))
//                        .productCategory(productCategoryService.getById(32L))
//                        .store(storeService.getStoreByName("Semeli"))
//                        .description("Best Pizza in town!").sizes(Sizes.LARGE)
//                        .sauces(Sauces.NONE).flavours(Flavours.NONE)
//                        .toppings(Toppings.BACON).toppings(Toppings.PEPERONI).build());
//
//                //ADD MORE PRODUCTS AS NEEDED
//
//        var productsCreated = productService.createAll(products);
//        logger.info("Created {} products.", productsCreated.size());
//        productsCreated.stream()
//                .sorted(Comparator.comparing(Product::getId))
//                .forEach(p -> logger.debug("{}. {}", p.getId(), p));
//
/*       List<User> customersCreated = customerService.createAll(
                User.builder()
                        .email("c.giannacoulis@codehub.gr")
                        .phone("1234567890")
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
                .phone("987654321")
                .password("strongPass456")
               .age(30)
                //.address("123 Main Street")
               .addressList(Arrays.asList(
                       Address.builder().address("Main Street").streetNumber(123).city("AnotherCity").build()))
                .firstName("John")
              .lastName("Doe")
               // .city("AnotherCity")
                .paymentMethod(PaymentMethod.PAYPAL)
                .role(Role.USER)
                .build(),
                User.builder()
                        .email("jane.smith@example.com")
                        .phone("555555555")
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
                        .phone("111223344")
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
                        .build());*/
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

    /*    logger.info("Created {} customers.", customersCreated.size());
        customersCreated.stream()
                .sorted(Comparator.comparing(User::getId))
                .forEach(c -> logger.debug("{}. {}", c.getId(), c)); */
    }
}
