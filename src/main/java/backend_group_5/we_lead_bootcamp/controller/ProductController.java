package backend_group_5.we_lead_bootcamp.controller;

import backend_group_5.we_lead_bootcamp.mapper.BaseMapper;
import backend_group_5.we_lead_bootcamp.mapper.ProductMapper;
import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.service.BaseService;
import backend_group_5.we_lead_bootcamp.service.ProductService;
import backend_group_5.we_lead_bootcamp.transfer.ApiResponse;
import backend_group_5.we_lead_bootcamp.transfer.resource.ProductResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //createProduct
    @RequestMapping("create")
    @PostMapping
    public ResponseEntity<ApiResponse<ProductResource>>createProduct(@RequestBody final ProductResource productResource,
                                                              @RequestParam Long productCategoryId){
        var product=productMapper.toDomain(productResource);
        return new ResponseEntity<>(
            ApiResponse.<ProductResource>builder().
                data(getMapper().toResource(productService.createProduct(product,productCategoryId)))
                .build(),
            getNoCacheHeaders(),
            HttpStatus.CREATED
     );
    }
    //findBySerial
    @GetMapping(params = "serial")
    public ResponseEntity<ApiResponse<ProductResource>> findBySerial(@RequestParam String serial){
        final ProductResource productResource= getMapper().toResource(productService.findBySerial(serial));
        return ResponseEntity.ok(ApiResponse.<ProductResource>builder().data(productResource).build());
    }
    //updateProduct
    @RequestMapping("update")
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@RequestBody final ProductResource productResource){
        var product=productMapper.toDomain(productResource);
        productService.updateProduct(product);
    }

    //deleteProduct
    @RequestMapping("delete")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@RequestBody final ProductResource productResource){
        var product=productMapper.toDomain(productResource);
        productService.deleteProduct(product);
    }
    //deleteProductById
    @RequestMapping("deleteById")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@RequestBody final ProductResource productResource) {
        var productId = productMapper.toDomain(productResource);
        productService.deleteProductById(productId.getId());
    }
    //countProduct
    @RequestMapping("count")
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void countProduct(@RequestBody final ProductResource productResource){
        var product=productMapper.toDomain(productResource);
        productService.countProducts();
    }




}
