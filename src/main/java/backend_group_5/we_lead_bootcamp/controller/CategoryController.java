package backend_group_5.we_lead_bootcamp.controller;

import backend_group_5.we_lead_bootcamp.mapper.BaseMapper;
import backend_group_5.we_lead_bootcamp.mapper.CategoryMapper;
import backend_group_5.we_lead_bootcamp.model.Category;
import backend_group_5.we_lead_bootcamp.service.BaseService;
import backend_group_5.we_lead_bootcamp.service.CategoryService;
import backend_group_5.we_lead_bootcamp.transfer.resource.CategoryResource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CategoryController extends BaseController<Category, CategoryResource> {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    @Override
    protected BaseService<Category,Long>getBaseService(){
        return categoryService;
    }
    @Override
    protected BaseMapper<Category,CategoryResource>getMapper(){
        return categoryMapper;
    }


}
