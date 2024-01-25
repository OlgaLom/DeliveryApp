package backend_group_5.we_lead_bootcamp.mapper;

import backend_group_5.we_lead_bootcamp.model.Address;
import backend_group_5.we_lead_bootcamp.model.User;
import backend_group_5.we_lead_bootcamp.transfer.resource.AddressResource;
import backend_group_5.we_lead_bootcamp.transfer.resource.UserResource;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserResource>{

    // New method for mapping List<Address> to List<AddressResource>
    List<Address> toDomainAddressList(List<AddressResource> addressResourceList);

    List<AddressResource> toResourceAddressList(List<Address> addressList);

}
