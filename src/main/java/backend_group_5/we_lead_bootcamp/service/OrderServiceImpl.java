package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.*;
import backend_group_5.we_lead_bootcamp.repository.BaseRepository;
import backend_group_5.we_lead_bootcamp.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    protected BaseRepository<Order, Long> getRepository() {
        return orderRepository;
    }

    @Override
    public Order InitiateOrder(final User user, final Store store) {
        return Order.builder().user(user).store(store).build();
    }

    @Override
    public void addItem(final Order order,final Product product,final int qty) {
        // Create a function and check if one of the objects is empty
        if(checkNullability(order,product)){
            return;
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
            order.getOrderItems().add( newOrderItem(product,qty) );
        }

        logger.trace("Product[{}] added to Order[{}]",product,order);
    }

    @Override
    public void UpdateItem(Order order, Product product, int qty) {
        if (checkNullability(order,product)){
            return;
        }
        // With "removeif" method, we check if the product exists in the existing order collection
        order.getOrderItems().removeIf(oi_obj -> oi_obj.getProduct().getId().equals(product.getId()));
        // Then we added the product with a peace of mind
        order.getOrderItems().add(newOrderItem(product, qty));
        // ~~~~~~~~~~
        // MAYBE A FUTURE FEATURE
        // ~~~~~~~~~~
        // There is a different approach to these two steps. The other approach is to check if the product already exists in the collection getOrderItems and if it's exists then update just the quantity, if not added to the collection. This approach is better if we know that we will have many updates on the products. That depends on the real data flow of the app.

        //~~~~ TO DO ~~~~
        // add order number instead of order obj
        logger.trace("Product[{}] is updated for the order with number #{}",product,order);

    }

    @Override
    public void RemoveItem(Order order, Product product) {
        if (checkNullability(order,product)){
            return;
        }

        // Get the collection of order items and check if the product exists in the collection. If exists remove it.
        boolean isRemoved = order.getOrderItems().removeIf(oi_obj -> oi_obj.getProduct().getId().equals(product.getId()));

        if (isRemoved){
            logger.trace("Product[{}] is removed for the order with number #{}",product,order);
        }else{
            logger.trace("No removal performed. Product [{}] not found in order with number #{}.",product,order);
        }
    }
    @Override
    public void SelectPaymentMethod(Order order,User user) {
        order.setPaymentMethod(user.getPaymentMethod());
        logger.trace("Order Payment is set for order #{}",order);
    }


    // ~~~~ TO DO ~~~~
    // Add method about order note
    // Complete method Finalize Order
    @Override
    public Order FinalizeOrder(Order order){
        //Check if order object if it is empty
        if (!isOrderEmpty(order)){
            logger.warn("Order should have a user, at least one order item, and payment type defined before being able to finalize the order.");
            return null;
        }

        // Set order fields with proper values
        order.setCreateDate(new Date());
        // Create a temp obj for total price of order. Initialize it as zero
        BigDecimal temp_total = BigDecimal.ZERO;
        // loop though the order items and calculate the order total
        for (OrderItem oi_obj : order.getOrderItems()) {
            temp_total = temp_total.add(oi_obj.getPrice());
        }
        // Set the final order total
        order.setOrderTotal(temp_total);

        // Set Order status
        order.setOrderStatus(OrderStatus.IN_PROGRESS);

        // Complete finalization of order
        return create(order);

    }

    @Override
    public Order FindByOrderNumber(final String OrderNumber){
        return orderRepository.findByOrderNumber(OrderNumber);
    }


    private OrderItem newOrderItem(Product product, int qty){
        return OrderItem.builder().product(product).quantity(qty).price(product.getPrice()).build();
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
}
