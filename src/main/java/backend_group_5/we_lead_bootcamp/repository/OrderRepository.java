package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.*;
import backend_group_5.we_lead_bootcamp.model.enums.OrderStatus;
import backend_group_5.we_lead_bootcamp.transfer.KeyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

//The @Repository annotation is used to indicate that a class is a Data Access Object (DAO)
// [ its primary purpose is to inform Spring to manage the bean lifecycle and provide additional features specific to data access. ]
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("SELECT ord FROM Order ord WHERE ord.orderNumber = :orderNum")
    Order findByOrderNumber(final String orderNum);

    @Query("SELECT ord FROM Order ord WHERE CAST(ord.createDate AS DATE) = :orderDate")
    List<Order> findByOrderDate(LocalDate orderDate);

    @Query("SELECT ord FROM Order ord WHERE ord.orderStatus = :orderStatus")
    List<Order> findOrdersByOrderStatus(OrderStatus orderStatus);

    @Query("SELECT ord FROM Order ord WHERE ord.user.id = :userId")
    List<Order> findOrdersByUser(Long userId);

    @Query("SELECT ord FROM Order ord WHERE ord.store.id = :storeId")
    List<Order> findOrdersByStore(Long storeId);

    @Query("SELECT ord FROM Order ord WHERE CAST(ord.createDate AS DATE) >= :fromDate AND CAST(ord.createDate AS DATE) <= :untilDate AND ord.orderTotal >= :total")
    List<Order> findOrdersByRangedDateAndAboveTotal(LocalDate fromDate, LocalDate untilDate, BigDecimal total);

    @Query("SELECT ord FROM Order ord WHERE CAST(ord.createDate AS DATE) >= :fromDate AND CAST(ord.createDate AS DATE) <= :untilDate AND ord.orderTotal <= :total")
    List<Order> findOrdersByRangedDateAndBelowTotal(LocalDate fromDate, LocalDate untilDate, BigDecimal total);

    @Query("SELECT ord FROM Order ord WHERE ord.orderTotal >= :total")
    List<Order> findOrdersByAboveTotal(BigDecimal total);

    @Query("SELECT ord FROM Order ord WHERE ord.orderTotal <= :total")
    List<Order> findOrdersByBelowTotal(BigDecimal total);

    @Query("SELECT ord FROM Order ord WHERE CAST(ord.createDate AS DATE) >= :fromDate AND  CAST(ord.createDate AS DATE) <= :UntilDate ")
    List<Order> findOrdersRangedDate(LocalDate fromDate, LocalDate UntilDate);

    @Query("SELECT ord FROM Order ord JOIN FETCH ord.orderItems oi WHERE oi.product.name LIKE %:orderItemName%")
    List<Order> findOrdersByOrderItem(String orderItemName);

    @Query("SELECT new backend_group_5.we_lead_bootcamp.transfer.KeyValue( st.name, SUM(ord.orderTotal) ) FROM Order ord JOIN ord.store st WHERE ord.store.id = st.id GROUP BY st.name ORDER BY SUM(ord.orderTotal) ASC")
    List<KeyValue<String, BigDecimal>> findOrdersByStoresRevenues();

    @Query("SELECT ord " +
            "FROM Order ord "+
            "JOIN FETCH ord.orderAddressList addr " +
            "WHERE addr.address LIKE %:ordAddress% OR addr.streetNumber = :ordStreetNum OR addr.city LIKE %:ordCity%")
    List<Order> findOrdersByAddress(String ordAddress, Integer ordStreetNum, String ordCity);

}
