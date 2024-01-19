package backend_group_5.we_lead_bootcamp.mapper;

import backend_group_5.we_lead_bootcamp.model.User;
import backend_group_5.we_lead_bootcamp.transfer.resource.UserResource;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",config = IgnoreUnmappedMapperConfig.class)
public interface UserMapper extends BaseMapper<User, UserResource> {
    @Override
     UserResource  toResource(User user);

    @Override
    List<UserResource> toResources(List<User> user);
    @Override
    User toDomain(UserResource userResource);
}
