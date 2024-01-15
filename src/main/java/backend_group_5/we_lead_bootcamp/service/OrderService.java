package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.*;

public interface OrderService extends BaseService<Order, Long>{
    Order initiateOrder(User user, Store store);
//    We probably need to add the store also
//    Order InitiateOrder(User user, Store store);

    void addItem(Order order, Product product, int quantity);

    void updateItem(Order order, Product product, int quantity);

    void removeItem(Order order, Product product);

    Order finalizeOrder(Order order, PaymentMethod paymentMethod, Address address,String orderNote);

    Order findByOrderNumber(String OrderNumber);

}
