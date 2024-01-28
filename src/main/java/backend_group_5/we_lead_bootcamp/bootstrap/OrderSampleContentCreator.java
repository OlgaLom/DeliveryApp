package backend_group_5.we_lead_bootcamp.bootstrap;

import backend_group_5.we_lead_bootcamp.base.BaseComponent;
import backend_group_5.we_lead_bootcamp.model.*;
import backend_group_5.we_lead_bootcamp.model.enums.*;
import backend_group_5.we_lead_bootcamp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Profile("generate-orders")
@RequiredArgsConstructor
public class OrderSampleContentCreator extends BaseComponent implements CommandLineRunner {
    private final UserService customerService;
    private final StoreService storeService;
    private final OrderService orderService;
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;

    @Override
    public void run(String... args) {
        // Get all customers
        orderService.findAll().forEach(ord -> logger.info("All Orders → {}", ord));

        // We don't mind if a "find" method returns a null
        logger.info("Does customer exist? {}.", (customerService.findByEmail("c.giannacoulis@codehub.gr") != null));
        logger.info("Does customer exist? {}.", (customerService.findByEmail("non-existing@gmail.com") != null));

//        ~~~~~
//        CREATE NEW USERS
//        ~~~~~
        List<User> UsersCreated = customerService.createAll(
                User.builder()
                        .email("c.giannacoulis@codehub.gr")
                        .phone("12345678901")
                        .password("securePassword123")
                        .age(47)
                        .addressList(Arrays.asList(
                                Address.builder().address("Tennessee Avenue").streetNumber(3583).city("SomeCity").build()))
                        .firstName("Constantinos")
                        .lastName("Giannacoulis")
                        .paymentMethod(PaymentMethod.CREDIT_CARD)
                        .role(Role.USER)
                        .build(),
                User.builder()
                        .email("OlgaMoum@email.com")
                        .phone("4521358942")
                        .password("securePassword155523")
                        .age(30)
                        .addressList(Arrays.asList(
                                Address.builder().address("Thessaloniki Avenue").streetNumber(21).city("SomeCity").build()))
                        .firstName("Olga")
                        .lastName("Moumtzi")
                        .paymentMethod(PaymentMethod.CREDIT_CARD)
                        .role(Role.USER)
                        .build()
                );
        logger.info("New Users→ {}.",UsersCreated );
//        ~~~~~
//        CREATE NEW STORE
//        ~~~~~
        Store newStore1 = storeService.create(Store.builder()
                .name("Helga Store")
                .address("Salonika")
                .phone("1235214593")
                .vatNumber("FP-6521354")
                .minOrderAmount(BigDecimal.valueOf(5))
                .category(StoreCategoryVariation.COCKTAILS)
                .DeliveryTime(30).products(new ArrayList<>())
                .build());

        logger.info("New store → {}.",newStore1 );

//        ~~~~~
//        CREATE NEW products
//        ~~~~~
        // Create and save the ProductCategory first
        ProductCategory productCategory = productCategoryService.create(ProductCategory.builder()
                .name("Εδεσματα")
                .description("Δειτε τα σπιτικα εδεσματα μας")
                .build());
//        ProductCategory productCategory2 = productCategoryService.create(ProductCategory.builder()
//                .name("Ποτά")
//                .description("Δειτε τα σπιτικα ποτακια μας")
//                .build());

        Product newProduct = productService.create(Product.builder()
                .name("Product Test Name")
                .price(BigDecimal.valueOf(3.20))
                .description("this is a very unique product")
                .productCategory(productCategoryService.getById(1L))
                .store(storeService.getStoreByName("Helga Store"))
                .serial("ANK52133")
                .sizes(Sizes.NONE)
                .flavours(Flavours.HAZELNUT)
                .sauces(Sauces.KETCHUP)
                .toppings(Toppings.BACON)
                .build());
        logger.info("New Product → {}.",newProduct );

       ;

//        Product newProduct2 = productService.create(Product.builder()
//                .name("Product Test Name 2")
//                .price(BigDecimal.valueOf(9.20))
//                .description("this is a very unique product 2")
//                .productCategory(productCategoryService.getById(11L))
//                .store(storeService.getStoreByName("Helga Store"))
//                .serial("ANK529898")
//                .sizes(Sizes.LARGE)
//                .flavours(Flavours.STRAWBERRY)
//                .sauces(Sauces.TZATZIKI)
//                .toppings(Toppings.CHEESE)
//                .build());
//        logger.info("New Product → {}.",newProduct2 );

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        // Load customer and store and create an order by adding/updating/removing content before finalizing it
//        User firstCustomer = customerService.findByEmail("c.giannacoulis@codehub.gr");
////        logger.info("firstCustomer → {}.",firstCustomer.getEmail() );
//        Store firstStore = storeService.getStoreByName("Helga Store");
////        logger.info("firstStore → {}.",firstStore.getName() );
//        Order firstOrder = orderService.initiateOrder(firstCustomer,firstStore);
//
//        // Add item(s) both existing and non-existing
//        orderService.addItem(firstOrder, productService.findBySerial("ANK529898"), 2);
//        orderService.addItem(firstOrder, productService.findBySerial("ANK52133"), 1);
////        logger.info("Order Items → {}.", firstOrder.getOrderItems() );
//        // Add a non-existing product
////        orderService.addItem(firstOrder, productService.findBySerial("SN1000-0008"), 1);
//        // Update item(s)
//        orderService.addItem(firstOrder, productService.findBySerial("ANK529898"), 1);
//        orderService.updateItem(firstOrder, productService.findBySerial("ANK52133"), 2);
//        // Remove item(s)
//        orderService.removeItem(firstOrder, productService.findBySerial("ANK529898"));
//        // Add some more item(s)
//        orderService.addItem(firstOrder, productService.findBySerial("ANK529898"), 5);
//
////      Address addr1 = firstCustomer.getAddress();
//        OrderAddress addr1 = OrderAddress.builder().address("Triandria spiti").streetNumber(52).city("Salonika").build();
//
//        // Checkout order
//        orderService.finalizeOrder(firstOrder, PaymentMethod.PAYPAL,addr1,"");



//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        // Load customer and store and create an order by adding/updating/removing content before finalizing it
//        User SecondCustomer = customerService.findByEmail("OlgaMoum@email.com");
////        logger.info("firstCustomer → {}.",firstCustomer.getEmail() );
//        Store secondStore = storeService.getStoreByName("Helga Store");
////        logger.info("firstStore → {}.",firstStore.getName() );
//        Order secondOrder = orderService.initiateOrder(SecondCustomer,secondStore);
//
//        // Add item(s) both existing and non-existing
//        orderService.addItem(secondOrder, productService.findBySerial("ANK529898"), 9);
//        orderService.addItem(secondOrder, productService.findBySerial("ANK52133"), 1);
////        logger.info("Order Items → {}.", firstOrder.getOrderItems() );
//        // Add a non-existing product
////        orderService.addItem(firstOrder, productService.findBySerial("SN1000-0008"), 1);
//        // Update item(s)
//        orderService.addItem(secondOrder, productService.findBySerial("ANK529898"), 1);
//        orderService.updateItem(secondOrder, productService.findBySerial("ANK52133"), 2);
//        // Remove item(s)
//        orderService.removeItem(secondOrder, productService.findBySerial("ANK529898"));
//        // Add some more item(s)
//        orderService.addItem(secondOrder, productService.findBySerial("ANK529898"), 5);
//
////      Address addr1 = firstCustomer.getAddress();
//        OrderAddress addr2 = OrderAddress.builder().address("Triandria work").streetNumber(33).city("Patra").build();
//
//        // Checkout order
//        orderService.finalizeOrder(secondOrder, PaymentMethod.CREDIT_CARD,addr2,"By doorbell is not working, just honk and i will open the door");

    }
}
