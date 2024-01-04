package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Order;
import backend_group_5.we_lead_bootcamp.model.OrderItem;
import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.User;
import backend_group_5.we_lead_bootcamp.repository.BaseRepository;
import backend_group_5.we_lead_bootcamp.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService{
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

        // Get the colletion of order items and check if the product exists in the collection. If exists remove it.
        boolean isRemoved = order.getOrderItems().removeIf(oi_obj -> oi_obj.getProduct().getId().equals(product.getId()));

        if (isRemoved){
            logger.trace("Product[{}] is removed for the order with number #{}",product,order);
        }else{
            logger.trace("No removal performed. Product [{}] not found in order with number #{}.",product,order);
        }
    }

    @Override
    public void SelectAddress(User user) {

    }

    @Override
    public void SelectPaymentMethod(User user) {

    }

    // ~~~~ TO DO ~~~~
    // Create method Finalize Order

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

    @Override
    public List<Order> createAll(Order... items) {
        return null;
    }
}
