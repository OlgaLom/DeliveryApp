package backend_group_5.we_lead_bootcamp.mapper;

import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.transfer.resource.StoreResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface StoreMapper extends BaseMapper<Store, StoreResource> {

    /*@Override
    public StoreResource toResource(Store entity) {
        StoreResource resource = super.toResource(entity);
    return resource;
    }


    @Override
    public Store toEntity(StoreResource resource) {
        Store entity = super.toEntity(resource);
        return entity;
    }

    */
}
