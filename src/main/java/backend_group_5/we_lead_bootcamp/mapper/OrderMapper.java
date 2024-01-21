package backend_group_5.we_lead_bootcamp.mapper;

import backend_group_5.we_lead_bootcamp.model.Order;
import backend_group_5.we_lead_bootcamp.transfer.resource.OrderResource;
import org.mapstruct.Mapper;

import java.util.List;

// @Mapper
// → Generates implementation code for mapping between Java beans, eliminating the need for boilerplate code.
@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface OrderMapper extends BaseMapper<Order, OrderResource>{

    List<OrderResource> toResourceList(List<Order> userOrders);
}
