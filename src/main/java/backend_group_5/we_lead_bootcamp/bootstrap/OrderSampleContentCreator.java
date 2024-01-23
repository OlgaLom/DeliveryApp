package backend_group_5.we_lead_bootcamp.bootstrap;

import backend_group_5.we_lead_bootcamp.base.BaseComponent;
import backend_group_5.we_lead_bootcamp.model.*;
import backend_group_5.we_lead_bootcamp.service.OrderService;
import backend_group_5.we_lead_bootcamp.service.ProductService;
import backend_group_5.we_lead_bootcamp.service.StoreService;
import backend_group_5.we_lead_bootcamp.service.UserService;
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
        customerService.findAll().forEach(c -> logger.info("{}", c));

        // We don't mind if a "find" method returns a null
        logger.info("Does customer exist? {}.", (customerService.findByEmail("c.giannacoulis@codehub.gr") != null));
        logger.info("Does customer exist? {}.", (customerService.findByEmail("non-existing@gmail.com") != null));


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        // Load customer and store and create an order by adding/updating/removing content before finalizing it
        User firstCustomer = customerService.findByEmail("c.giannacoulis@codehub.gr");
        Store firstStore = storeService.getStoreByName("Store1");
        Order firstOrder = orderService.initiateOrder(firstCustomer,firstStore);

        // Add item(s) both existing and non-existing
        orderService.addItem(firstOrder, productService.findBySerial("SN1000-0001"), 2);
        orderService.addItem(firstOrder, productService.findBySerial("SN1100-0001"), 1);
        orderService.addItem(firstOrder, productService.findBySerial("SN1000-0004"), 1);
        // Add a non-existing product
        orderService.addItem(firstOrder, productService.findBySerial("SN1000-0008"), 1);
        // Update item(s)
        orderService.addItem(firstOrder, productService.findBySerial("SN1000-0001"), 1);
        orderService.updateItem(firstOrder, productService.findBySerial("SN1000-0004"), 2);
        // Remove item(s)
        orderService.removeItem(firstOrder, productService.findBySerial("SN1100-0001"));
        // Add some more item(s)
        orderService.addItem(firstOrder, productService.findBySerial("SN1300-0001"), 2);

//      Address addr1 = firstCustomer.getAddress();
        Address addr1 = new Address("Triandria spiti",21,"Salonika");

        // Checkout order
        orderService.finalizeOrder(firstOrder, PaymentMethod.PAYPAL,addr1,null);
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Second customer and order
        User secondCustomer = customerService.findByEmail("john.porter@gmailx.com");
        Store secondStore = storeService.getStoreByName("Store2");

        //      Address addr1 = firstCustomer.getAddress();
        Address addr2 = new Address("Triandria spiti",21,"Salonika");


        Order secondOrder = orderService.initiateOrder(secondCustomer,secondStore);
        // Add item(s) to second order
        orderService.addItem(secondOrder, productService.findBySerial("SN1000-0002"), 1);
        orderService.addItem(secondOrder, productService.findBySerial("SN1200-0001"), 1);
        orderService.addItem(secondOrder, productService.findBySerial("SN1200-0001"), 1);
        orderService.addItem(secondOrder, productService.findBySerial("SN1299-0001"), 1);
        // Checkout 2nd order
        orderService.finalizeOrder(secondOrder, PaymentMethod.CREDIT_CART,addr2,"The ring bell is not working, Just yell my name and i will open up");
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        // Third customer and order
        User thirdCustomer = customerService.findByEmail("malcolm.paker@gmailx.com");

        Store thirdStore = storeService.getStoreByName("Store3");

        //      Address addr1 = firstCustomer.getAddress();
        Address addr3 = new Address("Triandria spiti",21,"Salonika");

        Order thirdOrder = orderService.initiateOrder(thirdCustomer,thirdStore);
        orderService.addItem(thirdOrder, productService.findBySerial("SN1000-0001"), 3);
        orderService.addItem(thirdOrder, productService.findBySerial("SN1000-0002"), 2);
        orderService.addItem(thirdOrder, productService.findBySerial("SN1000-0003"), 1);
        // Checkout 3rd order
        orderService.finalizeOrder(thirdOrder, PaymentMethod.CREDIT_CART,addr3,null);
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        // Fourth customer and order
        User fourthCustomer = customerService.findByEmail("terry.jones@gmailx.com");
        Store fourthStore = storeService.getStoreByName("Store4");

        //      Address addr1 = firstCustomer.getAddress();
        Address addr4 = new Address("Triandria spiti",21,"Salonika");

        Order fourthOrder = orderService.initiateOrder(fourthCustomer,fourthStore);
        orderService.addItem(fourthOrder, productService.findBySerial("SN1300-0001"), 1);
        orderService.addItem(fourthOrder, productService.findBySerial("SN1400-0001"), 2);
        orderService.addItem(fourthOrder, productService.findBySerial("SN1500-0001"), 1);
        orderService.addItem(fourthOrder, productService.findBySerial("SN1000-0003"), 1);
        orderService.addItem(fourthOrder, productService.findBySerial("SN1000-0004"), 1);
        // Checkout 4th order
        orderService.finalizeOrder(fourthOrder, PaymentMethod.PAYPAL,addr4,null);



    }
}
