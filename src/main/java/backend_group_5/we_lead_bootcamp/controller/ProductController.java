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
import backend_group_5.we_lead_bootcamp.transfer.resource.ProductResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("products")
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
    @RequestMapping("/products/update")
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@RequestBody final ProductResource productResource){
        var product=productMapper.toDomain(productResource);
        productService.update(product);
    }

    //deleteProduct
    @RequestMapping("/products/delete")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@RequestBody final ProductResource productResource){
        var product=productMapper.toDomain(productResource);
        productService.delete(product);
    }
    //deleteProductById
    @RequestMapping("/products/deleteById")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@RequestBody final ProductResource productResource) {
        var productId = productMapper.toDomain(productResource);
        productService.deleteById(productId.getId());
    }
    //countProduct
    @RequestMapping("/products/count")
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void countProduct(final ProductResource productResource){
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
    //getVariationBySize
    @GetMapping(params = {"sizes"})
    public ResponseEntity<ApiResponse<Sizes>> getVariationBySize
    (@RequestParam String productName,@RequestParam Sizes sizes){
        final Sizes variationSize= productService.getVariationSize(sizes, productName);
        return ResponseEntity.ok(ApiResponse.<Sizes>builder().data(variationSize).build());
    }
    //getVariationByFlavour
    @GetMapping(params = {"flavours"})
    public ResponseEntity<ApiResponse<Flavours>> getVariationByFlavours
    (@RequestParam String productName,@RequestParam Flavours flavours){
        final Flavours variationFlavours= productService.getVariationFlavour(flavours,productName);
        return ResponseEntity.ok(ApiResponse.<Flavours>builder().data(variationFlavours).build());
    }
    //getVariationBySauces
    @GetMapping(params = {"sauces"})
    public ResponseEntity<ApiResponse<Sauces>> getVariationBySauces
    (@RequestParam String productName,@RequestParam Sauces sauces){
        final Sauces variationSauces= productService.getVariationSauces(sauces,productName);
        return ResponseEntity.ok(ApiResponse.<Sauces>builder().data(variationSauces).build());
    }
    //getVariationByToppings
    @GetMapping(params = {"toppings"})
    public ResponseEntity<ApiResponse<Toppings>> getVariationByToppings
    (@RequestParam String productName,@RequestParam Toppings toppings){
        final Toppings variationToppings= productService.getVariationToppings(toppings,productName);
        return ResponseEntity.ok(ApiResponse.<Toppings>builder().data(variationToppings).build());
    }
    //getStore
    @GetMapping(params = {"store"})
    public ResponseEntity<ApiResponse<Store>> getStore(@RequestParam Store store){
        final Store stores= productService.getStore(store);
        return ResponseEntity.ok(ApiResponse.<Store>builder().data(stores).build());
    }




}
