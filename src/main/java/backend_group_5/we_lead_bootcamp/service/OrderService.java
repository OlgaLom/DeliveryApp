package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.*;
import backend_group_5.we_lead_bootcamp.model.enums.OrderStatus;
import backend_group_5.we_lead_bootcamp.model.enums.PaymentMethod;
import backend_group_5.we_lead_bootcamp.transfer.KeyValue;
import backend_group_5.we_lead_bootcamp.transfer.OrderByOrderNumber;
import jakarta.persistence.EnumType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface OrderService extends BaseService<Order, Long>{

    Order initiateOrder(User user, Store store);
//    We probably need to add the store also
//    Order InitiateOrder(User user, Store store);

    void addItem(Order order, Product product, int quantity);

    void updateItem(Order order, Product product, int quantity);

    void removeItem(Order order, Product product);

    Order finalizeOrder(Order order, PaymentMethod paymentMethod, OrderAddress address, String orderNote);

//    Order findByOrderNumber(String OrderNumber);
    OrderByOrderNumber<String,BigDecimal, EnumType, Date,EnumType,String,String,String,String> findByOrderNumber(String OrderNumber);
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

    List<Order> findOrdersByAddress(String address, Integer streetNumber,String city);

    List<KeyValue<String, BigDecimal>> findOrdersByStoresRevenues();

//    List<Order> findAllOrderWithUserData();

}
