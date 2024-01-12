package backend_group_5.we_lead_bootcamp.controller;

import backend_group_5.we_lead_bootcamp.mapper.BaseMapper;
import backend_group_5.we_lead_bootcamp.mapper.ProductCategoryMapper;
import backend_group_5.we_lead_bootcamp.model.ProductCategory;
import backend_group_5.we_lead_bootcamp.service.BaseService;
import backend_group_5.we_lead_bootcamp.service.ProductCategoryService;
import backend_group_5.we_lead_bootcamp.transfer.resource.ProductCategoryResource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ProductCategoryController extends BaseController<ProductCategory, ProductCategoryResource> {
    private final ProductCategoryService productCategoryService;
    private final ProductCategoryMapper productCategoryMapper;
    @Override
    protected BaseService<ProductCategory,Long>getBaseService(){
        return productCategoryService;
    }
    @Override
    protected BaseMapper<ProductCategory, ProductCategoryResource>getMapper(){
        return productCategoryMapper;
    }


}
