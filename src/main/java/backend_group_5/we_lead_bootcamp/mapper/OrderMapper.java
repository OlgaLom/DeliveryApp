package backend_group_5.we_lead_bootcamp.mapper;

import backend_group_5.we_lead_bootcamp.model.Order;
import backend_group_5.we_lead_bootcamp.transfer.resource.OrderResource;
import lombok.Builder;
import org.hibernate.Hibernate;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// @Mapper
// → Generates implementation code for mapping between Java beans, eliminating the need for boilerplate code.
@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface OrderMapper extends BaseMapper<Order, OrderResource>{


   @Mapping(target = "user", ignore = true)
   @Mapping(target = "store", ignore = true)
   @Mapping(target = "orderAddressList", ignore = true)
   @Mapping(target = "orderItems", ignore = true)
   @Named("toLightResource")
   OrderResource toLightResource(Order order);



   @Mapping(target = "user", ignore = true)
   @Mapping(target = "store", ignore = true)
   @Mapping(target = "orderAddressList", ignore = true)
   @Mapping(target = "orderItems", ignore = true)
   @Named("toLightResources")
   default List<OrderResource> toLightResources(List<Order> order){
      return order.stream().map(this::toLightResource).toList();
   }



}
