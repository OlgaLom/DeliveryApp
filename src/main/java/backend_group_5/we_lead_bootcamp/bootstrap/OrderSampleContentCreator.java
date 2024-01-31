package backend_group_5.we_lead_bootcamp.bootstrap;

import backend_group_5.we_lead_bootcamp.base.BaseComponent;
import backend_group_5.we_lead_bootcamp.model.*;
import backend_group_5.we_lead_bootcamp.model.enums.*;
import backend_group_5.we_lead_bootcamp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("generate-orders")
@RequiredArgsConstructor
public class OrderSampleContentCreator extends BaseComponent implements CommandLineRunner {
    private final UserService customerService;
    private final StoreService storeService;
    private final OrderService orderService;
    private final ProductService productService;

    @Override
    public void run(String... args) {
        // Get all customers
        orderService.findAll().forEach(ord -> logger.info("All Orders → {}", ord));

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        // Load customer and store and create an order by adding/updating/removing content before finalizing it
        User firstCustomer = customerService.findByEmail("john.doe@example.com");
        Store firstStore = storeService.getStoreByName("SOUVLAKI BAR");
        Order firstOrder = orderService.initiateOrder(firstCustomer,firstStore);

        // Add item(s) both existing and non-existing
        orderService.addItem(firstOrder, productService.findBySerial("SN1000-0001"), 2);
        orderService.addItem(firstOrder, productService.findBySerial("SN1000-0002"), 1);
 //        logger.info("Order Items → {}.", firstOrder.getOrderItems() );

        // Add a non-existing product
        orderService.addItem(firstOrder, productService.findBySerial("SN1000-6666"), 1);
        // Update item(s)
        orderService.addItem(firstOrder, productService.findBySerial("SN1000-0001"), 3);
        orderService.updateItem(firstOrder, productService.findBySerial("SN1000-0002"), 2);
        // Remove item(s)
        orderService.removeItem(firstOrder, productService.findBySerial("SN1100-0002"));
        // Add some more item(s)
        orderService.addItem(firstOrder, productService.findBySerial("SN1100-0002"), 5);

//      Address addr1 = firstCustomer.getAddress();
        OrderAddress addr1 = OrderAddress.builder().address("Tennessee Avenue").streetNumber(3583).city("SomeCity").build();

        // Checkout order
        orderService.finalizeOrder(firstOrder, PaymentMethod.PAYPAL,addr1,"");



//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        // Load customer and store and create an order by adding/updating/removing content before finalizing it
        User SecondCustomer = customerService.findByEmail("alice.jones@example.com");
//        logger.info("firstCustomer → {}.",firstCustomer.getEmail() );
        Store secondStore = storeService.getStoreByName("DERLICATESSEN");
//        logger.info("firstStore → {}.",firstStore.getName() );
        Order secondOrder = orderService.initiateOrder(SecondCustomer,secondStore);

        // Add item(s) both existing and non-existing
        orderService.addItem(secondOrder, productService.findBySerial("SN1100-0002"), 13);


//      Address addr1 = firstCustomer.getAddress();
        OrderAddress addr2 = OrderAddress.builder().address("Elm Street").streetNumber(789).city("CityX").build();

        // Checkout order
        orderService.finalizeOrder(secondOrder, PaymentMethod.CREDIT_CARD,addr2,"");
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        // Load customer and store and create an order by adding/updating/removing content before finalizing it
        User thirdCustomer = customerService.findByEmail("mary.ferry@windowslivex.com");
        Store thirdStore = storeService.getStoreByName("SOUVLAKI BAR");
        Order thirdOrder = orderService.initiateOrder(thirdCustomer,thirdStore);

        // Add item(s) both existing and non-existing
        orderService.addItem(thirdOrder, productService.findBySerial("SN1100-0001"), 5);
        orderService.addItem(thirdOrder, productService.findBySerial("SN1100-0002"), 1);

        OrderAddress addr3 = OrderAddress.builder().address("place").streetNumber(1).city("coty").build();

        // Checkout order
        orderService.finalizeOrder(thirdOrder, PaymentMethod.CREDIT_CARD,addr3,"Don't ring the doorbell the baby is sleeping. Just call my number and i will open the door for you");

 //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        // Load customer and store and create an order by adding/updating/removing content before finalizing it
        User fourthCustomer = customerService.findByEmail("jane.smith@example.com");
        Store fourthStore = storeService.getStoreByName("CREPOMANIAC");
        Order fourthOrder = orderService.initiateOrder(fourthCustomer,fourthStore);

        // Add item(s) both existing and non-existing
        orderService.addItem(fourthOrder, productService.findBySerial("SN1100-0001"), 7);
//        orderService.addItem(fourthOrder, productService.findBySerial("SN1000-0002"), 1);
//        orderService.addItem(fourthOrder, productService.findBySerial("SN1100-0001"), 1);
//        orderService.addItem(fourthOrder, productService.findBySerial("SN1100-0002"), 2);
//        orderService.addItem(fourthOrder, productService.findBySerial("SN1100-0003"), 4);


        OrderAddress addr4 = OrderAddress.builder().address("place").streetNumber(1).city("coty").build();

        // Checkout order
        orderService.finalizeOrder(fourthOrder, PaymentMethod.CREDIT_CARD,addr4,"Don't ring the doorbell the baby is sleeping. Just call my number and i will open the door for you");

    }
}
