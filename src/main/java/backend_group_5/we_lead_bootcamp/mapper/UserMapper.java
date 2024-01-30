package backend_group_5.we_lead_bootcamp.mapper;

import backend_group_5.we_lead_bootcamp.model.User;
import backend_group_5.we_lead_bootcamp.model.enums.PaymentMethod;
import backend_group_5.we_lead_bootcamp.transfer.resource.UserResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",config = IgnoreUnmappedMapperConfig.class)
public interface UserMapper extends BaseMapper<User, UserResource>{

    // New method for mapping List<Address> to List<AddressResource>
    //List<Address> toDomainAddressList(List<AddressResource> addressResourceList);
    //@Transactional
    //List<AddressResource> toResourceAddressList(List<Address> addressList);

    PaymentMethod toDomainPaymentMethod(PaymentMethod paymentMethod);
}
