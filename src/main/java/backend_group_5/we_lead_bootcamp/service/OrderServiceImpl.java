package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.*;
import backend_group_5.we_lead_bootcamp.repository.OrderRepository;
import backend_group_5.we_lead_bootcamp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {
    private final OrderRepository orderRepository;
//    private final UserRepository userRepository;

    @Override
    protected JpaRepository<Order, Long> getRepository() {
        return orderRepository;
    }
//    @Override
//    protected JpaRepository<User, Long> getUserRepository() {
//        return userRepository;
//    }

    @Override
    public Order initiateOrder(final User user, final Store store) {
        return Order.builder().user(user).store(store).build();
    }

    @Override
    public void addItem(final Order order,final Product product,final int qty) {
        // Create a function and check if one of the objects is empty
        if(checkNullability(order,product)){
            return;
        }
        // Check if the store id of the current order is different of the store id of the product that we are trying to add to the order. If the store id is not the same that means that the user is adding a product from a different store. So we need to clear the order items of the order and also change the store.
        if ( ! order.getStore().getId().equals(product.getStore().getId() )  ){
            order.getOrderItems().clear();
            order.setStore(product.getStore());
        }

        boolean inc_quantity = false;

        // Check if item is already exist in the order object. If so don't add it again and just increase the quantity.
        for (OrderItem oi_obj : order.getOrderItems() ){
            if (oi_obj.getProduct().getId().equals(product.getId())){
                oi_obj.setQuantity(oi_obj.getQuantity()+qty);
                inc_quantity = true;
                break;
            }
        }

        // if quantity doesn't get increased then the product is not in the list, we must add it
        if (!inc_quantity){
            order.getOrderItems().add( newOrderItem(order,product,qty) );
        }

        logger.trace("Product[{}] added to Order[{}]",product,order);
    }

    @Override
    public void updateItem(final Order order,final Product product, int qty) {
        if (checkNullability(order,product)){
            return;
        }
        // With "removeif" method, we check if the product exists in the existing order collection
        order.getOrderItems().removeIf(oi_obj -> oi_obj.getProduct().getId().equals(product.getId()));
        // Then we added the product with a peace of mind
        order.getOrderItems().add(newOrderItem(order, product, qty));
        // ~~~~~~~~~~
        // MAYBE A FUTURE FEATURE
        // ~~~~~~~~~~
        // There is a different approach to these two steps. The other approach is to check if the product already exists in the collection getOrderItems and if it's exists then update just the quantity, if not added to the collection. This approach is better if we know that we will have many updates on the products. That depends on the real data flow of the app.

        logger.trace("Product[{}] is updated for the order {}",product.getSerial(),order);

    }

    @Override
    public void removeItem(final Order order,final Product product) {
        if (checkNullability(order,product)){
            return;
        }

        // Get the collection of order items and check if the product exists in the collection. If exists remove it.
        boolean isRemoved = order.getOrderItems().removeIf(oi_obj -> oi_obj.getProduct().getId().equals(product.getId()));

        if (isRemoved){
            logger.trace("Product[{}] is removed for the order {}",product.getSerial(),order);
        }else{
            logger.trace("No removal performed. Product [{}] not found in order {}.",product.getSerial(),order);
        }
    }

    @Override
    public Order finalizeOrder(final Order order,final PaymentMethod paymentMethod,final Address address, final String orderNote){
        //Check order object if it is empty
        if (!isOrderEmpty(order)){
            logger.warn("Order should have a user, at least one order item, and payment type defined before being able to finalize the order.");
            return null;
        }
        // Set creation date for order
        order.setCreateDate(new Date());
        // Create a temp obj for total price of order. Initialize it as zero
        BigDecimal temp_total = BigDecimal.ZERO;
        // loop though the order items and calculate the order total
        for (OrderItem oi_obj : order.getOrderItems()) {
            temp_total = temp_total.add(oi_obj.getPrice());
        }
        // Set the final order total
        order.setOrderTotal(temp_total);

        // Set Payment Method
        order.setPaymentMethod(paymentMethod);

        // Set Address
        order.setAddress(address);
        // Set order note if any
        if ( !orderNote.isEmpty() )
            order.setOrderNote(orderNote);

        // Set Order status
        order.setOrderStatus(OrderStatus.IN_PROGRESS);

        // Create and set Unique Order number
        order.setOrderNumber( generateOrderNumber() );

        // Complete finalization of order
        return create(order);

    }

    @Override
    public Order findByOrderNumber(final String OrderNumber){
        return orderRepository.findByOrderNumber(OrderNumber);
    }

//    @Override
//    public List<Order> findOrdersByUser(Long userId){
//        Optional<User> userOpt = userRepository().findById(userId);
//    }

    @Override
    public List<Order> findOrdersByDate(LocalDate orderDate){
        return orderRepository.findByOrderDate(orderDate);
    }

    private OrderItem newOrderItem(Order order, Product product, int qty){
        return OrderItem.builder().order(order).product(product).quantity(qty).price(product.getPrice()).build();
    }
    private boolean checkNullability(Order orderObj, Product prod){
        if (orderObj == null ){
            logger.warn("The oder Object is null :(");
            return true;
        }
        if(prod == null){
            logger.warn("The product object is null :(");
            return true;
        }
        return false;
    }

    private boolean isOrderEmpty(Order order){
        return order != null && !order.getOrderItems().isEmpty() && order.getUser() != null && order.getPaymentMethod() != null;

    }

    // Generates a unique order number combining random letters and a timestamp.
    private String generateOrderNumber(){

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder ord_num = new StringBuilder();

        // Add 4 random letters
        for (int i = 0; i < 4; i++) {
            //ThreadLocalRando: Generates a random integer between 0 and given number, in our cache alphabet length
            int randomIndex = ThreadLocalRandom.current().nextInt(alphabet.length());
            ord_num.append(alphabet.charAt(randomIndex));
        }
        // Generate a timestamp part
        ord_num.append(ZonedDateTime.now().format(DateTimeFormatter.ofPattern( "yyMMddhhmmss" )));

        // EXAMPLE ADAC240118100056
        // Return the order number
        return ord_num.toString();
    }
}
