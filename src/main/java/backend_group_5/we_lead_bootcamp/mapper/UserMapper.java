package backend_group_5.we_lead_bootcamp.mapper;

import backend_group_5.we_lead_bootcamp.model.User;
import backend_group_5.we_lead_bootcamp.model.enums.PaymentMethod;
import backend_group_5.we_lead_bootcamp.transfer.resource.UserResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring",config = IgnoreUnmappedMapperConfig.class)
public interface UserMapper extends BaseMapper<User, UserResource>{

    @Mapping(target = "addressList", ignore = true)
    @Mapping(target = "password",ignore = true)
    @Named("toLightResource")
    UserResource toLightResource(User user);
    PaymentMethod toDomainPaymentMethod(PaymentMethod paymentMethod);
    @Mapping(target = "addressList",ignore = true)
    @Named("toLightResources")
   default List<UserResource> toLightResources(List<User> users) {
        return users.stream().map(this::toLightResource).toList();
    }
}
