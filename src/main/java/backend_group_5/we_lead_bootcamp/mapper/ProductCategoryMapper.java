package backend_group_5.we_lead_bootcamp.mapper;

import backend_group_5.we_lead_bootcamp.model.ProductCategory;
import backend_group_5.we_lead_bootcamp.transfer.resource.ProductCategoryResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",config = IgnoreUnmappedMapperConfig.class)
public interface ProductCategoryMapper extends BaseMapper<ProductCategory, ProductCategoryResource> {
}
