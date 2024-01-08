package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.Category;

public interface CategoryRepository extends BaseRepository<Category,Long>{
    Category findByDescription(String description);
}
