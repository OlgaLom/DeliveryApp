package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Order;
import backend_group_5.we_lead_bootcamp.model.Product;

public interface OrderService extends BaseService<Order, Long>{
    Order InitiateOrder(User user);

    void addItem(Order order, Product product);

    void UpdateItem(Order order, Product product);

    void RemoveItem(Order order,Product product);

    void SelectAddress(User user);

    void SelectPaymentMethod(User user);

}
