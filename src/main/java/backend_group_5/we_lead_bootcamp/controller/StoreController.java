package backend_group_5.we_lead_bootcamp.controller;

import backend_group_5.we_lead_bootcamp.mapper.BaseMapper;
import backend_group_5.we_lead_bootcamp.mapper.StoreMapper;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.service.BaseService;
import backend_group_5.we_lead_bootcamp.service.StoreService;
import backend_group_5.we_lead_bootcamp.transfer.resource.StoreResource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stores")
@RequiredArgsConstructor
public class StoreController extends BaseController <Store, StoreResource> {

    private final StoreService storeService;
    private final StoreMapper storeMapper;

    @Override
    protected BaseService<Store, Long> getBaseService() {
        return storeService;
    }
    @Override
    protected BaseMapper<Store, StoreResource> getMapper() {
        return storeMapper;
    }
    //...
}
