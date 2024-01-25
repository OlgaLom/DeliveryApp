package backend_group_5.we_lead_bootcamp.mapper;

import backend_group_5.we_lead_bootcamp.model.Address;
import backend_group_5.we_lead_bootcamp.transfer.resource.AddressResource;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface AddressMapper extends BaseMapper<Address, AddressResource>{

}
