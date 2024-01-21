package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.StoreCategory;
import backend_group_5.we_lead_bootcamp.repository.StoreCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreCategoryServiceImpl extends BaseServiceImpl<StoreCategory> implements StoreCategoryService {

    private final StoreCategoryRepository storeCategoryRepository;

    @Override
    protected JpaRepository<StoreCategory, Long> getRepository() {
        return storeCategoryRepository;
    }

    @Autowired
    public StoreCategoryServiceImpl(StoreCategoryRepository storeCategoryRepository) {
        this.storeCategoryRepository = storeCategoryRepository;
    }

    @Override
    public StoreCategory createCategory(StoreCategory category) {
        return storeCategoryRepository.save(category);
    }

    @Override
    public List<StoreCategory> getAllCategories() {
        return storeCategoryRepository.findAll();
    }

    // Additional methods for custom business logic related to store categories
}
