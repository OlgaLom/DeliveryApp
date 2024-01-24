package backend_group_5.we_lead_bootcamp.bootstrap;

import backend_group_5.we_lead_bootcamp.base.BaseComponent;
import backend_group_5.we_lead_bootcamp.model.*;
import backend_group_5.we_lead_bootcamp.service.ProductCategoryService;
import backend_group_5.we_lead_bootcamp.service.ProductService;
import backend_group_5.we_lead_bootcamp.service.StoreService;
import backend_group_5.we_lead_bootcamp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Component
@Profile("generate-catalog-customers")
@RequiredArgsConstructor
public class CatalogCustomerSampleContentCreator extends BaseComponent implements CommandLineRunner {
    private final ProductService productService;
    private final ProductCategoryService categoryService;
    private final UserService customerService;
    private final StoreService storeService;
    @Override
    public void run(String... args) throws Exception {
        ProductCategory newCategory = categoryService.create(ProductCategory.builder().description("Coffee").build());
        logger.info("Created {}.", newCategory);
        Store newStore= storeService.create(Store.builder().build());
        logger.info("Created {}!",newStore);
        //Variation variations= productService.getVariationSize();

//        ProductVariations.Sizes size = ProductVariations.Sizes.SMALL;
//        ProductVariations.Flavours flavour = ProductVariations.Flavours.CARAMEL;

        List<Product> products = List.of(
                Product.builder().serial("SN1000-0001").name("Fredo Espresso")
                        .price(BigDecimal.valueOf(1629)).productCategory(newCategory)
                        .description("Hot espresso shaken with ice cubes").store(newStore)
                        .sizes(ProductVariations.Sizes.SMALL).build(),
                Product.builder().serial("SN1000-0002").name("Apple iPhone 12 Pro Max 5G 512GB")
                        .price(BigDecimal.valueOf(1749)).productCategory(newCategory).build(),
                Product.builder().serial("SN1100-0001").name("Samsung Galaxy S21 Ultra")
                        .price(BigDecimal.valueOf(1479.99)).productCategory(newCategory).build(),
                Product.builder().serial("SN1100-0002").name("Samsung Galaxy S20 Ultra")
                        .price(BigDecimal.valueOf(1199)).productCategory(newCategory).build(),
                Product.builder().serial("SN1200-0001").name("Huawei P40 Pro")
                        .price(BigDecimal.valueOf(899.49)).productCategory(newCategory).build(),
                Product.builder().serial("SN1300-0001").name("Xiaomi Redmi 9A")
                        .price(BigDecimal.valueOf(199.75)).productCategory(newCategory).build(),
                Product.builder().serial("SN1400-0001").name("RealMe C11")
                        .price(BigDecimal.valueOf(129)).productCategory(newCategory).build(),
                Product.builder().serial("SN1500-0001").name("Honor 10 Lite")
                        .price(BigDecimal.valueOf(179)).productCategory(newCategory).build(),
                Product.builder().serial("SN1000-0003").name("Apple iPhone 12 Pro Max 5G 128GB")
                        .price(BigDecimal.valueOf(1339)).productCategory(newCategory).build(),
                Product.builder().serial("SN1000-0004").name("Apple iPhone 11 Pro 256GB")
                        .price(BigDecimal.valueOf(1299.99)).productCategory(newCategory).build());

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
                        .address("3583 Tennessee Avenue")
                        .firstName("Constantinos")
                        .lastName("Giannacoulis")
                        .city("SomeCity")
                        .paymentMethod(PaymentMethod.CREDIT_CARD)
                        .role(Role.USER)
                        .build(),
                User.builder().email("john.doe@example.com")
                .phone(987654321)
                .password("strongPass456")
                .age(30)
                .address("123 Main Street")
                .firstName("John")
                .lastName("Doe")
                .city("AnotherCity")
                .paymentMethod(PaymentMethod.PAYPAL)
                .role(Role.ADMIN)
                .build(),
                User.builder()
                        .email("jane.smith@example.com")
                        .phone(555555555)
                        .password("securePass789")
                        .age(25)
                        .address("456 Oak Avenue")
                        .firstName("Jane")
                        .lastName("Smith")
                        .city("YetAnotherCity")
                        .paymentMethod(PaymentMethod.COD)
                        .role(Role.USER)
                        .build(),
                User.builder()
                        .email("alice.jones@example.com")
                        .phone(111223344)
                        .password("password123")
                        .age(28)
                        .address("789 Elm Street")
                        .firstName("Alice")
                        .lastName("Jones")
                        .city("CityX")
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
