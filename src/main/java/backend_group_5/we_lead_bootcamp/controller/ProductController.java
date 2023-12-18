package backend_group_5.we_lead_bootcamp.controller;

import backend_group_5.we_lead_bootcamp.mapper.BaseMapper;
import backend_group_5.we_lead_bootcamp.mapper.ProductMapper;
import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.service.BaseService;
import backend_group_5.we_lead_bootcamp.service.ProductService;
import backend_group_5.we_lead_bootcamp.transfer.resource.ProductResource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController extends BaseController<Product, ProductResource>{
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Override
    protected BaseService<Product,Long> getBaseService(){return productService;}

    @Override
    protected BaseMapper<Product, ProductResource> getMapper(){return productMapper;}


}
