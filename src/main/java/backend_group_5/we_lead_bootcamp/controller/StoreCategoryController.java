package backend_group_5.we_lead_bootcamp.controller;

import backend_group_5.we_lead_bootcamp.mapper.BaseMapper;
import backend_group_5.we_lead_bootcamp.mapper.StoreCategoryMapper;
import backend_group_5.we_lead_bootcamp.model.StoreCategory;
import backend_group_5.we_lead_bootcamp.service.BaseService;
import backend_group_5.we_lead_bootcamp.service.StoreCategoryService;
import backend_group_5.we_lead_bootcamp.transfer.resource.StoreCategoryResource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store-categories")
@RequiredArgsConstructor
public class StoreCategoryController extends BaseController<StoreCategory, StoreCategoryResource> {

    private final StoreCategoryService storeCategoryService;
    private final StoreCategoryMapper storeCategoryMapper;

    @Override
    protected BaseService<StoreCategory, Long> getBaseService() {
        return storeCategoryService;
    }

    @Override
    protected BaseMapper<StoreCategory, StoreCategoryResource> getMapper() {
        return storeCategoryMapper;
    }
}
