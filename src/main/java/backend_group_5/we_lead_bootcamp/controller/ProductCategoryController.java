package backend_group_5.we_lead_bootcamp.controller;

import backend_group_5.we_lead_bootcamp.mapper.BaseMapper;
import backend_group_5.we_lead_bootcamp.mapper.ProductCategoryMapper;
import backend_group_5.we_lead_bootcamp.model.ProductCategory;
import backend_group_5.we_lead_bootcamp.service.BaseService;
import backend_group_5.we_lead_bootcamp.service.ProductCategoryService;
import backend_group_5.we_lead_bootcamp.transfer.ApiResponse;
import backend_group_5.we_lead_bootcamp.transfer.resource.ProductCategoryResource;
import backend_group_5.we_lead_bootcamp.transfer.resource.ProductResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("productCategory")
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

    @GetMapping(params = {"description"})
    public ResponseEntity<ApiResponse<ProductCategoryResource>> findByDescription(@RequestParam final String description){
        return ResponseEntity.ok(ApiResponse.<ProductCategoryResource>builder()
                .data(getMapper().toResource(productCategoryService.getByDescription(description)))
                .build());
    }

    @GetMapping("/{productCategoryName}")
    public ResponseEntity<ApiResponse<List<ProductCategoryResource>>> getProductCategoryName(
            @PathVariable("productCategoryName") final String name){
        List<ProductCategory> productCategoryName=productCategoryService.getProductCategoryName(name);
        return ResponseEntity.ok(
                ApiResponse.<List<ProductCategoryResource>>builder()
                        .data(productCategoryMapper.toResources(productCategoryName)).build());
    }

}
