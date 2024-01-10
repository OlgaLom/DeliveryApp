package backend_group_5.we_lead_bootcamp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {}/*extends BaseController<Product, ProductResource>{
    private final ProductService productService;
    private final ProductMapper productMapper;

    @Override
    protected BaseService<Product,Long> getBaseService(){return productService;}

    @Override
    protected BaseMapper<Product, ProductResource> getMapper(){return productMapper;}


}*/
