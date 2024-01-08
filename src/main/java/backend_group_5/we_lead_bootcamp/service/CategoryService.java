package backend_group_5.we_lead_bootcamp.service;

import backend_group_5.we_lead_bootcamp.model.Category;

public interface CategoryService extends BaseService<Category, Long> {
    Category findByDescription(String description);
}
