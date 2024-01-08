package backend_group_5.we_lead_bootcamp.service;


import backend_group_5.we_lead_bootcamp.model.Category;
import backend_group_5.we_lead_bootcamp.repository.BaseRepository;
import backend_group_5.we_lead_bootcamp.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends BaseServiceImpl<Category>implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    protected BaseRepository<Category,Long>getRepository(){
        return categoryRepository;
    }

    @Override
    public Category findByDescription(final String description){
        return categoryRepository.findByDescription(description);
    }

    @Override
    public List<Category> createAll(Category... items) {
        return null;
    }
}
