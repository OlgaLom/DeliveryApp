package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.*;
import backend_group_5.we_lead_bootcamp.model.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface OrderService extends BaseService<Order, Long>{
    Order initiateOrder(User user, Store store);
//    We probably need to add the store also
//    Order InitiateOrder(User user, Store store);

    void addItem(Order order, Product product, int quantity);

    void updateItem(Order order, Product product, int quantity);

    void removeItem(Order order, Product product);

    Order finalizeOrder(Order order, PaymentMethod paymentMethod, OrderAddress address, String orderNote);

    Order findByOrderNumber(String OrderNumber);

    List<Order> findOrdersByDate(LocalDate orderDate);

    List<Order> findOrdersByOrderStatus( OrderStatus orderStatus);

    List<Order> findOrdersByUser(Long userId);

    List<Order> findOrdersByStore(Long storeId);
    List<Order> findOrdersDateRange(LocalDate fromDate,LocalDate untilDate);

    List<Order> findOrdersByDateRangeAndAboveTotal(LocalDate fromDate,LocalDate untilDate, BigDecimal total);

    List<Order> findOrdersByDateRangeAndBelowTotal(LocalDate fromDate,LocalDate untilDate, BigDecimal total);

    List<Order> findOrdersByAboveTotal(BigDecimal total);
    List<Order> findOrdersByBelowTotal(BigDecimal total);


    List<Order> findOrdersByOrderItem(String orderItemName);

    List<Order> findOrdersByAddress(OrderAddress orderAddress);

    List<Object[]>  findOrdersByStoresRevenues();




}
