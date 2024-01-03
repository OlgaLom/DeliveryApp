package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.Order;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

//The @Repository annotation is used to indicate that a class is a Data Access Object (DAO)
// [ its primary purpose is to inform Spring to manage the bean lifecycle and provide additional features specific to data access. ]
@Repository
public interface OrderRepository extends BaseRepository<Order,Long>{
    Order findByOrderNumber(final String orderNum);
}
