package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.ProductVariations;
import backend_group_5.we_lead_bootcamp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService{
    private final ProductRepository productRepository;
    private final ProductCategoryService productCategoryService;
    @Override
    protected JpaRepository<Product, Long> getRepository()    {
        return productRepository;
    }

    @Override
    public Product findBySerial(final String serial) {
        return productRepository.findBySerial(serial);
    }
    @Override
    public Product getProductDescription(final String description) {
        return productRepository.findByDescription(description);
    }
    @Override
    public List<Product> listAllProducts() {
        return findAll();
    }

    @Override
    public Product createProduct(Product product, final Long categoryId) {
        var category=productCategoryService.getById(categoryId);
        product.setProductCategory(category);
        return productRepository.save(product);
    }
    @Override
    public void updateProduct(Product product) {
        getRepository().save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        getRepository().delete(product);
    }

    @Override
    public void deleteProductById(Long productId) {
        getRepository().deleteById(productId);
    }

    @Override
    public long countProducts() {
        return getRepository().count();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean productExists(Product productId) {
        logger.trace("Checking whether {} exists!",productId);
        return getRepository().existsById(productId.getId());
    }

    @Override
    public Product getProductById(Product product,Long id) {
//        if(getRepository().getReferenceById(id)==null){
//            throw new NoSuchElementException(String.format("Product with id [%d] not found",id));
//        }
        return getRepository().getReferenceById(id);
    }

    @Override
    public Product getProductName(String name,Long id) {
        return productRepository.findByName(name);
    }

    @Override
    public Product getProductPrice(BigDecimal price) {
        return productRepository.findByPrice(price);
    }
    @Override
    public List<ProductVariations> getVariationSize(String productName, ProductVariations.Size size) {
        return  productRepository.getVariationBySize(productName, size);
    }

    @Override
    public List<ProductVariations> getVariationFlavour(String productName, ProductVariations.Flavours flavours) {
        return productRepository.getVariationByFlavours(productName, flavours);
    }
    @Override
    public List<ProductVariations> getVariationSauces(String productName, ProductVariations.Sauces sauces) {
        return productRepository.getVariationBySauces(productName,sauces);
    }
    @Override
    public List<ProductVariations> getVariationToppings(String productName, ProductVariations.Toppings toppings) {
        return productRepository.getVariationByToppings(productName,toppings);
    }
    @Override
    public Store getStore(Store store) {
        return store;
    }

}
