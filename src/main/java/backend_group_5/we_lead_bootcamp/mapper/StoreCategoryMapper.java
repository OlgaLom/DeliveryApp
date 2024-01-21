package backend_group_5.we_lead_bootcamp.mapper;

import backend_group_5.we_lead_bootcamp.model.StoreCategory;
import backend_group_5.we_lead_bootcamp.transfer.resource.StoreCategoryResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface StoreCategoryMapper extends BaseMapper<StoreCategory, StoreCategoryResource> {
}
