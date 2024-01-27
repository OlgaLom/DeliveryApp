package backend_group_5.we_lead_bootcamp.mapper;

import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.transfer.resource.ProductResource;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring",config=IgnoreUnmappedMapperConfig.class)
public interface ProductMapper extends BaseMapper<Product, ProductResource>{
}
