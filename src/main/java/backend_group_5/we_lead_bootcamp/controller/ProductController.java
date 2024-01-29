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
    public ResponseEntity<ApiResponse<ProductResource>> getProductBySerial
    (@PathVariable("serial") final String serial){

        return ResponseEntity.ok(
                ApiResponse.<ProductResource>builder().data(productMapper.toResource(productService.findBySerial(serial)))
                        .build());
    }
    //getProductName
    @GetMapping("/productName/{name}")
    public ResponseEntity<ApiResponse<ProductResource>> getProductByName
    (@PathVariable("name") final String name){
        final ProductResource productResource= getMapper().toResource(productService.getProductName(name));
        return ResponseEntity.ok(ApiResponse.<ProductResource>builder().data(productResource).build());
    }
    //getProductById
    @GetMapping("/productId/{productId}")
    public ResponseEntity<ApiResponse<ProductResource>> getProductById
    (@PathVariable("productId") final Long productId){
        final ProductResource productResource= getMapper().toResource(productService.getById(productId));
        return ResponseEntity.ok(ApiResponse.<ProductResource>builder().data(productResource).build());
    }
    //getProductByPrice
    @GetMapping("/productPrice/{price}")
    public ResponseEntity<ApiResponse<ProductResource>> getProductByPrice
    (@PathVariable("price") final BigDecimal price){
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
    @GetMapping("/productSize/{size}")
    public ResponseEntity<ApiResponse<List<ProductResource>>> getVariationBySize
    (@PathVariable("size") final Sizes sizes){
        List<Product> productSizes= productService.getProductBySize(sizes);
        return ResponseEntity.ok(ApiResponse.<List<ProductResource>>builder()
                .data(productMapper.toResources(productSizes))
                .build());
    }
    //getVariationByFlavour
    @GetMapping("/productFlavour/{flavour}")
    public ResponseEntity<ApiResponse<List<ProductResource>>> getVariationByFlavour
    (@PathVariable("flavour") final Flavours flavours){
        List<Product> productFlavours= productService.getProductByFlavour(flavours);
        return ResponseEntity.ok(ApiResponse.<List<ProductResource>>builder()
                .data(productMapper.toResources(productFlavours))
                .build());
    }
    //getVariationBySauces
    @GetMapping("/productSauces/{sauces}")
    public ResponseEntity<ApiResponse<List<ProductResource>>> getVariationBySauces
    (@PathVariable("sauces") final Sauces sauces){
        List<Product> productSauces= productService.getProductBySauces(sauces);
        return ResponseEntity.ok(ApiResponse.<List<ProductResource>>builder()
                .data(productMapper.toResources(productSauces))
                .build());    }
    //getVariationByToppings

    @GetMapping("/productToppings/{toppings}")
    public ResponseEntity<ApiResponse<List<ProductResource>>> getProductsByToppings
    (@PathVariable final Toppings toppings){
        List<Product> productToppings= productService.getProductByToppings(toppings);
        return ResponseEntity.ok(ApiResponse.<List<ProductResource>>builder()
                .data(productMapper.toResources(productToppings))
                .build());
    }
    //getStore
    @GetMapping( "/store/{storeId}")
    public ResponseEntity<ApiResponse<List<ProductResource>>> getProductsByStore
    (@PathVariable("storeId") final Long storeId){
        List<Product> storeProducts=productService.findProductsByStore(storeId);
        return ResponseEntity.ok(
                ApiResponse.<List<ProductResource>>builder()
                        .data(productMapper.toResources(storeProducts))
                        .build());
    }




}
