package backend_group_5.we_lead_bootcamp.controller;

import backend_group_5.we_lead_bootcamp.mapper.BaseMapper;
import backend_group_5.we_lead_bootcamp.mapper.ProductMapper;
import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.enums.Flavours;
import backend_group_5.we_lead_bootcamp.model.enums.Sauces;
import backend_group_5.we_lead_bootcamp.model.enums.Sizes;
import backend_group_5.we_lead_bootcamp.model.enums.Toppings;
import backend_group_5.we_lead_bootcamp.service.BaseService;
import backend_group_5.we_lead_bootcamp.service.ProductService;
import backend_group_5.we_lead_bootcamp.transfer.ApiResponse;
import backend_group_5.we_lead_bootcamp.transfer.resource.ProductResource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    //@RequestMapping("create")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ProductResource>>createProduct
    (@RequestBody final ProductResource productResource){
        var product=getBaseService().create(getMapper().toDomain(productResource));
        return new ResponseEntity<>(
            ApiResponse.<ProductResource>builder().
                data(getMapper().toResource(product)).build(),

            getNoCacheHeaders(),
            HttpStatus.CREATED
     );
    }

    //updateProduct
//    @Transactional
//    @PutMapping("/update")
////    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public ResponseEntity<ProductResource> updateProduct(@RequestBody final ProductResource productResource){
//        Product product=productMapper.toDomain(productResource);
//        Product updatedProduct=getBaseService(update(productResource));
//        ProductResource updatedProductResource= productMapper.toResource(updatedProduct);
//        return ResponseEntity.ok(updatedProductResource);
//    }

    //deleteProductById
    @DeleteMapping("/delete/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable("productId") final Long productId) {
        getBaseService().deleteById(productId);
    }
    //countProduct
   // @RequestMapping()
    @PostMapping("/count")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public void countProduct(final ProductResource productResource){
        var product=productMapper.toDomain(productResource);
        productService.count();
    }
    //findBySerial
    @GetMapping("/serial/{serial}")
    public ResponseEntity<ApiResponse<ProductResource>> getProductBySerial(@PathVariable("serial") final String serial){

        return ResponseEntity.ok(
                ApiResponse.<ProductResource>builder().data(productMapper.toResource(productService.findBySerial(serial)))
                        .build());
    }
    //getProductName
    @GetMapping("/productName/{name}")
    public ResponseEntity<ApiResponse<ProductResource>> getProductByName(@PathVariable("name") final String name){
        final ProductResource productResource= getMapper().toResource(productService.getProductName(name));
        return ResponseEntity.ok(ApiResponse.<ProductResource>builder().data(productResource).build());
    }
    //getProductById
    @GetMapping("/productId/{productId}")
    public ResponseEntity<ApiResponse<ProductResource>> getProductById(@PathVariable("productId") final Long productId){
        final ProductResource productResource= getMapper().toResource(productService.getById(productId));
        return ResponseEntity.ok(ApiResponse.<ProductResource>builder().data(productResource).build());
    }
    //getProductByPrice
    @GetMapping("/productPrice/{price}")
    public ResponseEntity<ApiResponse<ProductResource>> getProductByPrice(@PathVariable("price") final BigDecimal price){
        final ProductResource productResource= getMapper().toResource(productService.getProductPrice(price));
        return ResponseEntity.ok(ApiResponse.<ProductResource>builder().data(productResource).build());
    }
    //getProductDescription
    @GetMapping("/productDescription/{description}")
    public ResponseEntity<ApiResponse<ProductResource>> getProductByDescription
    (@PathVariable("description") final String description){
        final ProductResource productResource= getMapper()
                .toResource(productService.getProductDescription(description));
        return ResponseEntity.ok(ApiResponse.<ProductResource>builder().data(productResource).build());
    }
    //getVariationBySize
    @GetMapping(params ={"productName","sizes"})
    public ResponseEntity<ApiResponse<List<ProductResource>>> getVariationBySize
    (@RequestParam String productName,@RequestParam Sizes sizes){
        final List<ProductResource> variationSize= productService.getVariationSize(productName,sizes);
        return ResponseEntity.ok(ApiResponse.<List<ProductResource>>builder().data(variationSize).build());
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
    public ResponseEntity<ApiResponse<Toppings>> getProductsByToppings
    (@RequestParam String productName,@PathVariable final Toppings toppings){
        final Toppings variationToppings= productService.getProductByToppings(toppings,productName);
        return ResponseEntity.ok(ApiResponse.<Toppings>builder().data(variationToppings).build());
    }
    //getStore
    @GetMapping( "/store/{storeId}")
    public ResponseEntity<ApiResponse<List<ProductResource>>> getProductsByStore(@PathVariable("storeId") final Long storeId){
        List<Product> storeProducts=productService.findProductsByStore(storeId);
        return ResponseEntity.ok(
                ApiResponse.<List<ProductResource>>builder()
                        .data(productMapper.toResources(storeProducts))
                        .build());
    }




}
