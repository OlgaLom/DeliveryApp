package backend_group_5.we_lead_bootcamp.controller;

import backend_group_5.we_lead_bootcamp.mapper.BaseMapper;
import backend_group_5.we_lead_bootcamp.mapper.ProductMapper;
import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.enums.Flavours;
import backend_group_5.we_lead_bootcamp.model.enums.Sauces;
import backend_group_5.we_lead_bootcamp.model.enums.Sizes;
import backend_group_5.we_lead_bootcamp.model.enums.Toppings;
import backend_group_5.we_lead_bootcamp.service.BaseService;
import backend_group_5.we_lead_bootcamp.service.ProductService;
import backend_group_5.we_lead_bootcamp.transfer.ApiResponse;
import backend_group_5.we_lead_bootcamp.transfer.resource.ProductCategoryResource;
import backend_group_5.we_lead_bootcamp.transfer.resource.ProductResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    public ResponseEntity<ApiResponse<ProductResource>>createProduct
    (@RequestBody final ProductResource productResource, @RequestParam Long productCategoryId){
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
    @GetMapping(params = {"serial"})
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
        productService.update(product);
    }

    //deleteProduct
    @RequestMapping("delete")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@RequestBody final ProductResource productResource){
        var product=productMapper.toDomain(productResource);
        productService.delete(product);
    }
    //deleteProductById
    @RequestMapping("deleteById")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@RequestBody final ProductResource productResource) {
        var productId = productMapper.toDomain(productResource);
        productService.deleteById(productId.getId());
    }
    //countProduct
    @RequestMapping("count")
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void countProduct(@RequestBody final ProductResource productResource){
        var product=productMapper.toDomain(productResource);
        productService.count();
    }
    //getProductName
    @GetMapping(params = {"name"})
    public ResponseEntity<ApiResponse<ProductResource>> getProductName(@RequestParam String name){
        final ProductResource productResource= getMapper().toResource(productService.getProductName(name));
        return ResponseEntity.ok(ApiResponse.<ProductResource>builder().data(productResource).build());
    }
    //getProductById
    @GetMapping(params = {"productId"})
    public ResponseEntity<ApiResponse<ProductResource>> getProductById(@RequestParam Long productId){
        final ProductResource productResource= getMapper().toResource(productService.getById(productId));
        return ResponseEntity.ok(ApiResponse.<ProductResource>builder().data(productResource).build());
    }
    //getProductByPrice
    @GetMapping(params = {"price"})
    public ResponseEntity<ApiResponse<ProductResource>> getProductByPrice(@RequestParam BigDecimal price){
        final ProductResource productResource= getMapper().toResource(productService.getProductPrice(price));
        return ResponseEntity.ok(ApiResponse.<ProductResource>builder().data(productResource).build());
    }
    //getProductDescription
    @GetMapping(params = {"description"})
    public ResponseEntity<ApiResponse<ProductResource>> getProductByDescription
    (@RequestParam final String description){
        final ProductResource productResource= getMapper()
                .toResource(productService.getProductDescription(description));
        return ResponseEntity.ok(ApiResponse.<ProductResource>builder().data(productResource).build());
    }
//    //getVariationBySize
//    @GetMapping(params = {"sizes"})
//    public ResponseEntity<ApiResponse<ProductResource>> getVariationBySize
//    (@RequestParam String productName,@RequestParam Sizes size){
//        final ProductResource productResource= getMapper()
//                .toResource(productService.getVariationSize(productName,size));
//        return ResponseEntity.ok(ApiResponse.<ProductResource>builder().data(productResource).build());
//    }
//    //getVariationByFlavour
//    @GetMapping(params = {"flavours"})
//    public ResponseEntity<ApiResponse<ProductResource>> getVariationByFlavours
//    (@RequestParam String productName,@RequestParam Flavours flavours){
//        final ProductResource productResource= getMapper()
//                .toResource(productService.getVariationFlavour(productName,flavours));
//        return ResponseEntity.ok(ApiResponse.<ProductResource>builder().data(productResource).build());
//    }
//    //getVariationBySauces
//    @GetMapping(params = {"sauces"})
//    public ResponseEntity<ApiResponse<ProductResource>> getVariationBySauces
//    (@RequestParam String productName,@RequestParam Sauces sauces){
//        final ProductResource productResource= getMapper()
//                .toResource(productService.getVariationSauces(productName,sauces));
//        return ResponseEntity.ok(ApiResponse.<ProductResource>builder().data(productResource).build());
//    }
//    //getVariationByToppings
//    @GetMapping(params = {"toppings"})
//    public ResponseEntity<ApiResponse<ProductResource>> getVariationByToppings
//    (@RequestParam String productName,@RequestParam Toppings toppings){
//        final ProductResource productResource= getMapper()
//                .toResource(productService.getVariationToppings(productName,toppings));
//        return ResponseEntity.ok(ApiResponse.<ProductResource>builder().data(productResource).build());
//    }
//    //getStore
//    @GetMapping(params = {"store"})
//    public ResponseEntity<ApiResponse<ProductResource>> getStore(@RequestParam Store store){
//        final ProductResource productResource= getMapper()
//                .toResource(productService.getStore(store));
//        return ResponseEntity.ok(ApiResponse.<ProductResource>builder().data(productResource).build());
  //  }
    //find all products
    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> findAll(@RequestParam Product products){
        final ProductResource productResource= getMapper().toResource((Product) productService.findAll());
        return ResponseEntity.ok(ApiResponse.<List<Product>>builder()
                .data((List<Product>) productResource).build());
    }



}
