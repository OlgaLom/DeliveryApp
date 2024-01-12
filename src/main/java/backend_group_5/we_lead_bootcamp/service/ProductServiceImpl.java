package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Category;
import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.repository.BaseRepository;
import backend_group_5.we_lead_bootcamp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    @Override
    public List<Product> createAll(List<Product> products) {
        return productRepository.createAll(products);
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
    public Product createProduct(Product product, final Long categoryId) {
        var category = categoryService.getById(categoryId);
        product.setCategory(category);
        return productRepository.create(product);
    }
}
