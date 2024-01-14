package backend_group_5.we_lead_bootcamp.service;


import backend_group_5.we_lead_bootcamp.model.ProductCategory;
import backend_group_5.we_lead_bootcamp.repository.BaseRepository;
import backend_group_5.we_lead_bootcamp.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl extends BaseServiceImpl<ProductCategory>implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    protected BaseRepository<ProductCategory,Long>getRepository(){
        return productCategoryRepository;
    }

    @Override
    public ProductCategory findByDescription(final String description){
        return productCategoryRepository.findByDescription(description);
    }

    @Override
    public List<ProductCategory> createAll(ProductCategory... items) {
        return null;
    }
}
