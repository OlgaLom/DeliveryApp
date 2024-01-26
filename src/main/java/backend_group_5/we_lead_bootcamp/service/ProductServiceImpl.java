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
        return productRepository.getProductBySerial(serial);
    }
    @Override
    public Product getProductDescription(final String description) {
        return productRepository.getProductByDescription(description);
    }
    @Override
    public List<Product> findAll() {
        return findAll();
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
    public Product getVariationSize(String productName, Sizes size) {
        return  productRepository.getVariationBySizes(productName, size);
    }

    @Override
    public Product getVariationFlavour(String productName, Flavours flavours) {
        return productRepository.getVariationByFlavours(productName, flavours);
    }
    @Override
    public Product getVariationSauces(String productName, Sauces sauces) {
        return productRepository.getVariationBySauces(productName,sauces);
    }
    @Override
    public Product getVariationToppings(String productName, Toppings toppings) {
        return productRepository.getVariationByToppings(productName,toppings);
    }
    @Override
    public Store getStore(Store store) {
        return store;
    }

}
