package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.*;
import backend_group_5.we_lead_bootcamp.model.enums.OrderStatus;
import backend_group_5.we_lead_bootcamp.transfer.KeyValue;
import backend_group_5.we_lead_bootcamp.transfer.OrderByOrderNumber;
import jakarta.persistence.EnumType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

//The @Repository annotation is used to indicate that a class is a Data Access Object (DAO)
// [ its primary purpose is to inform Spring to manage the bean lifecycle and provide additional features specific to data access. ]
@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
//    @Query(value = "SELECT ord.*" +
//            "FROM Order ord " +
//            "LEFT JOIN ord.user us " +
//            "JOIN FETCH ord.store st " +
//            "JOIN FETCH ord.orderAddressList adr " +
//            "JOIN FETCH ord.orderItems items " +
//            "WHERE ord.orderNumber = :orderNum" , nativeQuery = true)
    @Query("SELECT new backend_group_5.we_lead_bootcamp.transfer.OrderByOrderNumber( ord.orderNumber , ord.orderTotal,  ord.paymentMethod, ord.createDate, ord.orderStatus,ur.firstName, ur.lastName, ur.email ,  st.name ) " +
            "FROM Order ord " +
            "LEFT JOIN User ur ON ur.id = ord.id " +
            "JOIN Store st ON st.id = ord.id " +
            "JOIN OrderAddress adr ON adr.id = ord.id " +
            "JOIN OrderItem items ON items.id = ord.id " +
            "WHERE ord.orderNumber = :orderNum " +
            "group by ord.id")
    OrderByOrderNumber<String,BigDecimal, EnumType, Date,EnumType,String,String,String,String> findByOrderNumber(final String orderNum);

    @Query("SELECT ord FROM Order ord WHERE CAST(ord.createDate AS DATE) = :orderDate")
    List<Order> findByOrderDate(LocalDate orderDate);

    @Query("SELECT ord FROM Order ord WHERE ord.orderStatus = :orderStatus")
    List<Order> findOrdersByOrderStatus(OrderStatus orderStatus);

    @Query("SELECT ord FROM Order ord WHERE ord.user.id = :userId")
    List<Order> findOrdersByUser(Long userId);

    @Query("SELECT ord FROM Order ord WHERE ord.store.id = :storeId")
    List<Order> findOrdersByStore(Long storeId);

    @Query("SELECT ord " +
            "FROM Order ord " +
            "WHERE CAST(ord.createDate AS DATE) >= :fromDate " +
            "AND CAST(ord.createDate AS DATE) <= :untilDate " +
            "AND ord.orderTotal >= :total")
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

    @Query("SELECT new backend_group_5.we_lead_bootcamp.transfer.KeyValue( st.name, SUM(ord.orderTotal) ) " +
            "FROM Order ord " +
            "JOIN ord.store st " +
            "WHERE ord.store.id = st.id " +
            "GROUP BY st.name " +
            "ORDER BY SUM(ord.orderTotal) ASC")
    List<KeyValue<String, BigDecimal>> findOrdersByStoresRevenues();

    @Query("SELECT ord " +
            "FROM Order ord " +
            "JOIN FETCH ord.orderAddressList addr " +
            "WHERE (:ordAddress IS NULL OR addr.address LIKE %:ordAddress%) " +
            "AND (:ordStreetNum IS NULL OR addr.streetNumber = :ordStreetNum) " +
            "AND (:ordCity IS NULL OR addr.city LIKE %:ordCity%)")
    List<Order> findOrdersByAddress(String ordAddress, Integer ordStreetNum, String ordCity);

    @Query("SELECT ord FROM Order ord JOIN ord.user us")
    List<Order> findAllOrderWithUserData();

//    @Query("SELECT ord FROM Order ord")
//    List<Order> findAllOrders();

}
