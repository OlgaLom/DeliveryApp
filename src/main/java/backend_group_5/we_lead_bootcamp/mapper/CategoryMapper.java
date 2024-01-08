package backend_group_5.we_lead_bootcamp.mapper;

import backend_group_5.we_lead_bootcamp.model.Category;
import backend_group_5.we_lead_bootcamp.transfer.resource.CategoryResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",config = IgnoreUnmappedMapperConfig.class)
public interface CategoryMapper extends BaseMapper<Category, CategoryResource> {
}
