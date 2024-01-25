package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.*;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

//The @Repository annotation is used to indicate that a class is a Data Access Object (DAO)
// [ its primary purpose is to inform Spring to manage the bean lifecycle and provide additional features specific to data access. ]
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("SELECT ord FROM Order ord WHERE ord.orderNumber = :orderNum")
    Order findByOrderNumber(final String orderNum);

    @Query("SELECT ord FROM Order ord WHERE DATE(ord.createDate) = :orderDate")
    List<Order> findByOrderDate(LocalDate orderDate);

    @Query("SELECT ord FROM Order ord WHERE ord.orderStatus = :orderStatus")
    List<Order> findOrdersByOrderStatus(OrderStatus orderStatus);

    @Query("SELECT ord FROM Order ord JOIN FETCH ord.user WHERE ord.id = :userId")
    List<Order> findOrdersByUser(Long userId);

    @Query("SELECT ord FROM Order ord JOIN FETCH ord.store WHERE ord.id = :storeId")
    List<Order> findOrdersByStore(Long storeId);

    @Query("SELECT ord FROM Order ord WHERE DATE(ord.createDate) > :fromDate AND  DATE(ord.createDate) < :unitDate AND ord.orderTotal > :total")
    List<Order> findOrdersByRangedDateAndAboveTotal(LocalDate fromDate, LocalDate unitDate, BigDecimal total);

    @Query("SELECT ord FROM Order ord WHERE DATE(ord.createDate) > :fromDate AND  DATE(ord.createDate) < :unitDate AND ord.orderTotal < :total")
    List<Order> findOrdersByRangedDateAndBelowTotal(LocalDate fromDate, LocalDate UntilDate, BigDecimal total);

    @Query("SELECT ord FROM Order ord WHERE ord.orderTotal > :total")
    List<Order> findOrdersByAboveTotal(BigDecimal total);

    @Query("SELECT ord FROM Order ord WHERE ord.orderTotal < :total")
    List<Order> findOrdersByBelowTotal(BigDecimal total);

    @Query("SELECT ord FROM Order ord WHERE DATE(ord.createDate) > :fromDate AND  DATE(ord.createDate) < :unitDate ")
    List<Order> findOrdersRangedDate(LocalDate fromDate, LocalDate UntilDate);

    @Query("SELECT ord FROM Order ord JOIN FETCH ord.orderItems oi WHERE oi.product.name = :orderItemName")
    List<Order> findOrdersByOrderItem(String orderItemName);

    @Query("SELECT st, SUM(ord.orderTotal) AS maxRevenue " +
            "FROM Order ord " +
            "JOIN ord.store st " +
            "GROUP BY st")
    List<Object[]> findOrdersByStoresRevenues();


    @Query("SELECT ord " +
            "FROM Order ord "+
            "JOIN FETCH ord.orderAddressList addr " +
            "WHERE addr.address = :orderAddress AND addr.streetNumber = :ordStreetNum AND addr.city = :ordCity")
    List<Order> findOrdersByAddress(String ordAddress, Integer ordStreetNum, String ordCity);

}
