package backend_group_5.we_lead_bootcamp.mapper;

import backend_group_5.we_lead_bootcamp.model.User;
import backend_group_5.we_lead_bootcamp.model.enums.PaymentMethod;
import backend_group_5.we_lead_bootcamp.transfer.resource.UserResource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring",config = IgnoreUnmappedMapperConfig.class)
public interface UserMapper extends BaseMapper<User, UserResource>{

    @Mapping(target = "addressList", ignore = true)
    @Named("toLightResources")
    UserResource toLightResource(User user);
    PaymentMethod toDomainPaymentMethod(PaymentMethod paymentMethod);
}
