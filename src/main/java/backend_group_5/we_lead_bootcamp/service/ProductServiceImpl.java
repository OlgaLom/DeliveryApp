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
        return null;
    }
    @Override
    public List<Product> listAllProducts() {
        return null;
    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void deleteProduct(Product product) {

    }

    @Override
    public void deleteProductById(Long productId) {

    }

    @Override
    public long countProducts() {
        return 0;
    }

    @Override
    public boolean productExists(Product product) {
        return false;
    }

    @Override
    public Product getProduct(Product product) {
        return null;
    }

    @Override
    public Product getProductName(String name) {
        return null;
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
    @Override
    public Product createProduct(Product product, final Long categoryId) {
        var category=productCategoryService.getById(categoryId);
        product.setProductCategory(category);
        return productRepository.create(product);
    }
}
