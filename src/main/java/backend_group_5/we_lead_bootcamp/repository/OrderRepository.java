package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.Order;
import backend_group_5.we_lead_bootcamp.model.OrderStatus;
import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
