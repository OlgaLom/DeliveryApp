package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Order;
import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.User;

import java.util.List;

public interface OrderService extends BaseService<Order, Long>{
    Order InitiateOrder(User user, Store store);
//    We probably need to add the store also
//    Order InitiateOrder(User user, Store store);

    void addItem(Order order, Product product, int quantity);

    void UpdateItem(Order order, Product product, int quantity);

    void RemoveItem(Order order, Product product);

    void SelectAddress(User user);

    void SelectPaymentMethod(Order order,User user);

    void UpdateOrderNote(Order order);

    Order FinalizeOrder(Order order);

    Order FindByOrderNumber(String OrderNumber);

}
