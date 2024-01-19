package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.Variation;
import backend_group_5.we_lead_bootcamp.repository.BaseRepository;
import backend_group_5.we_lead_bootcamp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService{
    private final ProductRepository productRepository;
    private final ProductCategoryService productCategoryService;

    @Override
    public List<Product> createAll(Product... items) {
        return null;
    }



    @Override
    protected BaseRepository<Product, Long> getRepository()    {
        return productRepository;
    }

    @Override
    public Product findBySerial(final String serial) {
        return productRepository.findBySerial(serial);
    }
    @Override
    public List<Product> createAllProducts(List<Product> products) {
        return productRepository.createAll(products);
    }
    @Override
    public List<Product> listAllProducts() {
        return findAll();
    }

    @Override
    public Product createProduct(Product product, final Long categoryId) {
        var category=productCategoryService.getById(categoryId);
        product.setProductCategory(category);
        return productRepository.create(product);
    }
    @Override
    public void updateProduct(Product product) {
        getRepository().update(product);
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
    public boolean productExists(Product productId) {
        return productRepository.exists(productId);
    }

    @Override
    public Product getProductById(Product product,Long id) {
        if(getRepository().getById(id)==null){
            throw new NoSuchElementException(String.format("Product with id [%d] not found",id));
        }
        return getRepository().getById(id);
    }

    @Override
    public Product getProductName(String name,Long id) {
        if(getRepository().getById(id)==null){
            throw new NoSuchElementException(String.format("",id));
        }
        return getRepository().getById(id);
    }

    @Override
    public Product getProductPrice(BigDecimal price) {
        return null;
    }

    @Override
    public Product getProductDescription(String description) {
        return null;
    }

    @Override
    public Variation getVariationSize(Variation.Size size) {
        return null;
    }

    @Override
    public Variation getVariationFlavour(Variation.Flavours flavours) {
        return null;
    }
    @Override
    public Variation getVariationSauces(Variation.Sauces sauces) {
        return null;
    }
    @Override
    public Variation getVariationToppings(Variation.Toppings toppings) {
        return null;
    }
    @Override
    public Store getStore(Store store) {
        return store;
    }

}
