package backend_group_5.we_lead_bootcamp.mapper;
import backend_group_5.we_lead_bootcamp.model.User;
import backend_group_5.we_lead_bootcamp.transfer.resource.UserResource;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring",config = IgnoreUnmappedMapperConfig.class)
@Component
public interface UserMapper extends BaseMapper<User, UserResource>{

}
