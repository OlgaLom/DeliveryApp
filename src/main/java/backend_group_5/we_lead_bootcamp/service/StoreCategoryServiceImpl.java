package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.StoreCategory;
import backend_group_5.we_lead_bootcamp.model.StoreCategoryVariation;
import backend_group_5.we_lead_bootcamp.repository.StoreCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreCategoryServiceImpl extends BaseServiceImpl<StoreCategory> implements StoreCategoryService {

    private final StoreCategoryRepository storeCategoryRepository;

    @Autowired
    public StoreCategoryServiceImpl(StoreCategoryRepository storeCategoryRepository) {
        this.storeCategoryRepository = storeCategoryRepository;
    }

    @Override
    protected JpaRepository<StoreCategory, Long> getRepository() {
        return storeCategoryRepository;
    }

    public Optional<StoreCategory> getStoreCategoryByName(StoreCategoryVariation name) {
        return storeCategoryRepository.findByName(name);
    }

    @Override
    public StoreCategory createCategory(StoreCategory category) {
        return storeCategoryRepository.save(category);
    }

    @Override
    public List<StoreCategory> createAllCategories(List<StoreCategory> categories) {
        return storeCategoryRepository.saveAll(categories);
    }

    @Override
    public StoreCategory updateCategory(Long categoryId,StoreCategoryVariation newName) {
        StoreCategory existingCategory=getById(categoryId);
        if(existingCategory !=null) {
            existingCategory.setName(newName);
            return storeCategoryRepository.save(existingCategory);
        }
        return null;
    }
    @Override
    public void deleteCategory(Long categoryId) {
        storeCategoryRepository.deleteById(categoryId);
    }
    @Override
    public void deleteCategoryByName(StoreCategoryVariation name) {
        storeCategoryRepository.findByName(name).ifPresent(category -> storeCategoryRepository.deleteById(category.getId()));
    }
    @Override
    public StoreCategory getCategoryById(Long categoryId){
        return getById(categoryId);
    }
    @Override
    public boolean doesCategoryExist(Long categoryId) {
        return storeCategoryRepository.existsById(categoryId);
    }
    @Override
    public List<StoreCategory> findAllCategories() {
        return findAll();
    }
    @Override
    public long countCategories() {
        return count();
    }

    // Additional methods for custom business logic related to store categories
}
