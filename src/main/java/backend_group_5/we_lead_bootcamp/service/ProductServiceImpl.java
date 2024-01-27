package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.enums.Flavours;
import backend_group_5.we_lead_bootcamp.model.enums.Sauces;
import backend_group_5.we_lead_bootcamp.model.enums.Sizes;
import backend_group_5.we_lead_bootcamp.model.enums.Toppings;
import backend_group_5.we_lead_bootcamp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


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
        return productRepository.getProductBySerial(serial);
    }
    @Override
    public Product getProductDescription(final String description) {
        return productRepository.getProductByDescription(description);
    }

    @Override
    public Product createProduct(Product product, final Long categoryId) {
        var category=productCategoryService.getById(categoryId);
        product.setProductCategory(category);
        return productRepository.save(product);
    }
    @Override
    public void update(Product product) {
        getRepository().save(product);
    }

    @Override
    public void delete(Product product) {
        getRepository().delete(product);
    }

    @Override
    public void deleteById(Long productId) {
        getRepository().deleteById(productId);
    }

    @Override
    public Long count() {
        return getRepository().count();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Product productId) {
        logger.trace("Checking whether {} exists!",productId);
        return getRepository().existsById(productId.getId());
    }

    @Override
    public Product getById(Long id) {
        return getRepository().getReferenceById(id);
    }

    @Override
    public Product getProductName(String name) {
        return productRepository.getProductByName(name);
    }

    @Override
    public Product getProductPrice(BigDecimal price) {
        return productRepository.getProductByPrice(price);
    }
    @Override
    public Sizes getVariationSize(Sizes sizes,String productName) {
        return  productRepository.getVariationBySizes(sizes, productName);
    }

    @Override
    public Flavours getVariationFlavour(Flavours flavours, String productName) {
        return productRepository.getVariationByFlavours(flavours, productName );
    }
    @Override
    public Sauces getVariationSauces(Sauces sauces, String productName) {
        return productRepository.getVariationBySauces(sauces, productName);
    }
    @Override
    public Toppings getVariationToppings(Toppings toppings,String productName) {
        return productRepository.getVariationByToppings(toppings, productName);
    }
    @Override
    public Store getStore(Store store) {
        return store;
    }

}
