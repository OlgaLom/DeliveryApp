package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Order;
import backend_group_5.we_lead_bootcamp.model.PaymentMethod;
import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.User;
import backend_group_5.we_lead_bootcamp.model.Store;
public interface OrderService extends BaseService<Order, Long>{
    Order InitiateOrder(User user, Store store);
//    We probably need to add the store also
//    Order InitiateOrder(User user, Store store);

    void addItem(Order order, Product product, int quantity);

    void UpdateItem(Order order, Product product, int quantity);

    void RemoveItem(Order order, Product product);

    void SelectAddress(User user);

    void SelectPaymentMethod(User user);

    // Ways to create method for Finalize and order.
//    An order
    //Order FinalizeOrder(Order order, PaymentMethod p_method,  )


}
