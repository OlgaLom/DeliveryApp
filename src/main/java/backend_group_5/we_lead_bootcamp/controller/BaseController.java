package backend_group_5.we_lead_bootcamp.controller;

import backend_group_5.we_lead_bootcamp.base.BaseComponent;
import backend_group_5.we_lead_bootcamp.mapper.BaseMapper;
import backend_group_5.we_lead_bootcamp.model.BaseModel;
import backend_group_5.we_lead_bootcamp.service.BaseService;
import backend_group_5.we_lead_bootcamp.transfer.ApiResponse;
import backend_group_5.we_lead_bootcamp.transfer.resource.BaseResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseController<T extends BaseModel, R extends BaseResource> extends BaseComponent {
//    T stands for Type | R stands for Resource
    protected abstract BaseService<T, Long> getBaseService();
    protected abstract BaseMapper<T, R> getMapper();

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<R>> get(@PathVariable("id") final Long id){
        return ResponseEntity.ok(
                ApiResponse.<R>builder()
                        .data(getMapper().toResource(getBaseService().getById(id)))
                        .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<R>>> findAll(){
        return ResponseEntity.ok(
                ApiResponse.<List<R>>builder()
                        .data(getMapper().toResources(getBaseService().findAll()))
                        .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<R>> create(@RequestBody final R resource){
        var domain = getBaseService().create(getMapper().toDomain(resource));
        return new ResponseEntity<>(
                ApiResponse.<R>builder().data(getMapper().toResource(domain)).build(),
                HttpStatus.CREATED);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody final R resource){
        getBaseService().update(getMapper().toDomain(resource));
    }

}
