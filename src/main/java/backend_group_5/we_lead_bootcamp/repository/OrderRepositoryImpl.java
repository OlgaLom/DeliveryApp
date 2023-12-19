package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.Order;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class OrderRepositoryImpl extends BaseRepositoryImpl<Order> implements OrderRepository {

    private final ConcurrentHashMap<Long, Order> storage = new ConcurrentHashMap<>();

    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    public ConcurrentHashMap<Long, Order> getStorage() { return storage; }

    @Override
    public AtomicLong getSequence() { return sequence; }
}
