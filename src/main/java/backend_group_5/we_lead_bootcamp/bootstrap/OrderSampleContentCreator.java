package backend_group_5.we_lead_bootcamp.bootstrap;

import backend_group_5.we_lead_bootcamp.base.BaseComponent;
import backend_group_5.we_lead_bootcamp.model.Order;
import backend_group_5.we_lead_bootcamp.model.User;
import backend_group_5.we_lead_bootcamp.service.OrderService;
import backend_group_5.we_lead_bootcamp.service.ProductService;
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
    private final OrderService orderService;
    private final ProductService productService;

    @Override
    public void run(String... args) {
        // Get all customers
        customerService.findAll().forEach(c -> logger.info("{}", c));

        // We don't mind if a "find" method returns a null
        logger.info("Does customer exist? {}.", (customerService.findByEmail("c.giannacoulis@codehub.gr") != null));
        logger.info("Does customer exist? {}.", (customerService.findByEmail("non-existing@gmail.com") != null));

        // Load customer and create an order by adding/updating/removing content before checking it out
        User firstCustomer = customerService.findByEmail("c.giannacoulis@codehub.gr");
        Order firstOrder = orderService.initiateOrder(firstCustomer);

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
        // Checkout order
        orderService.checkout(firstOrder, PaymentMethod.CREDIT_CARD);

        // Second customer and order
        User secondCustomer = customerService.get(2L);
        Order secondOrder = orderService.initiateOrder(secondCustomer);
        // Add item(s) to second order
        orderService.addItem(secondOrder, productService.findBySerial("SN1000-0002"), 1);
        orderService.addItem(secondOrder, productService.findBySerial("SN1200-0001"), 1);
        orderService.addItem(secondOrder, productService.findBySerial("SN1200-0001"), 1);
        orderService.addItem(secondOrder, productService.findBySerial("SN1299-0001"), 1);
        // Checkout 2nd order
        orderService.checkout(secondOrder, PaymentMethod.CASH);

        // Third customer and order
        User thirdCustomer = customerService.findByEmail("malcolm.paker@gmailx.com");
        Order thirdOrder = orderService.initiateOrder(thirdCustomer);
        orderService.addItem(thirdOrder, productService.findBySerial("SN1000-0001"), 3);
        orderService.addItem(thirdOrder, productService.findBySerial("SN1000-0002"), 2);
        orderService.addItem(thirdOrder, productService.findBySerial("SN1000-0003"), 1);
        // Checkout 3rd order
        orderService.checkout(thirdOrder, PaymentMethod.CREDIT_CARD);

        // Fourth customer and order
        User fourthCustomer = customerService.findByEmail("terry.jones@gmailx.com");
        Order fourthOrder = orderService.initiateOrder(fourthCustomer);
        orderService.addItem(fourthOrder, productService.findBySerial("SN1300-0001"), 1);
        orderService.addItem(fourthOrder, productService.findBySerial("SN1400-0001"), 2);
        orderService.addItem(fourthOrder, productService.findBySerial("SN1500-0001"), 1);
        orderService.addItem(fourthOrder, productService.findBySerial("SN1000-0003"), 1);
        orderService.addItem(fourthOrder, productService.findBySerial("SN1000-0004"), 1);
        // Checkout 4th order
        orderService.checkout(fourthOrder, PaymentMethod.CREDIT_CARD);

        logger.debug("{}.", orderService.get(1L));

        // **** SOME EXTRA ORDERS FOR SHOWCASING WITHIN THE APPLICATION **** //
        // customer and order
        User c5 = customerService.get(5L);
        Order o5 = orderService.initiateOrder(c5);
        // Add item(s) to order
        orderService.addItem(o5, productService.findBySerial("SN1000-0002"), 4);
        orderService.addItem(o5, productService.findBySerial("SN1200-0001"), 2);
        orderService.addItem(o5, productService.findBySerial("SN1200-0001"), 1);
        // Checkout order
        orderService.checkout(o5, PaymentMethod.CASH);

        // customer and order
        User c6 = customerService.get(6L);
        Order o6 = orderService.initiateOrder(c6);
        // Add item(s) to order
        orderService.addItem(o6, productService.findBySerial("SN1200-0001"), 2);
        // Checkout order
        orderService.checkout(o6, PaymentMethod.CREDIT_CARD);

        // customer and order
        User c7 = customerService.get(7L);
        Order o7 = orderService.initiateOrder(c7);
        // Add item(s) to order
        orderService.addItem(o7, productService.findBySerial("SN1000-0002"), 4);
        orderService.addItem(o7, productService.findBySerial("SN1200-0001"), 1);
        // Checkout order
        orderService.checkout(o7, PaymentMethod.CREDIT_CARD);

        // customer and order
        User c8 = customerService.get(8L);
        Order o8 = orderService.initiateOrder(c8);
        // Add item(s) to order
        orderService.addItem(o8, productService.findBySerial("SN1000-0002"), 1);
        orderService.addItem(o8, productService.findBySerial("SN1200-0001"), 1);
        orderService.addItem(o8, productService.findBySerial("SN1200-0001"), 1);
        // Checkout order
        orderService.checkout(o8, PaymentMethod.CASH);

        // customer and order
        User c9 = customerService.get(9L);
        Order o9 = orderService.initiateOrder(c9);
        // Add item(s) to order
        orderService.addItem(o9, productService.findBySerial("SN1200-0001"), 1);
        // Checkout order
        orderService.checkout(o9, PaymentMethod.CASH);

        // customer and order
        User c10 = customerService.get(10L);
        Order o10 = orderService.initiateOrder(c10);
        // Add item(s) to order
        orderService.addItem(o10, productService.findBySerial("SN1200-0001"), 3);
        // Checkout order
        orderService.checkout(o10, PaymentMethod.CREDIT_CARD);
    }
}
