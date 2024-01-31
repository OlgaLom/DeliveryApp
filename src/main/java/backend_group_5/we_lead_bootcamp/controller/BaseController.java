package backend_group_5.we_lead_bootcamp.controller;

import backend_group_5.we_lead_bootcamp.base.BaseComponent;
import backend_group_5.we_lead_bootcamp.mapper.BaseMapper;
import backend_group_5.we_lead_bootcamp.model.BaseModel;
import backend_group_5.we_lead_bootcamp.model.enums.OrderStatus;
import backend_group_5.we_lead_bootcamp.service.BaseService;
import backend_group_5.we_lead_bootcamp.transfer.ApiResponse;
import backend_group_5.we_lead_bootcamp.transfer.resource.BaseResource;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.util.List;


public abstract class  BaseController<T extends BaseModel, R extends BaseResource> extends BaseComponent {
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
    public ResponseEntity<ApiResponse<R>> create(@Valid @RequestBody final R resource){
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

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") final Long id) {
        getBaseService().deleteById(id);
    }

    protected HttpHeaders getNoCacheHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        // HTTP 1.1 cache control header
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        // Http 1.0 cache control header
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return headers;
    }

    // Registers a custom editor for ENUM, this method adds insensitive support to ENUM Classes
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Add support for OrderStatus Class
        binder.registerCustomEditor(OrderStatus.class, new CaseInsensitiveEnumEditor(OrderStatus.class));
    }
    private static class CaseInsensitiveEnumEditor extends PropertyEditorSupport {
        private final Class<? extends Enum> enumType;

        public CaseInsensitiveEnumEditor(Class<? extends Enum> enumType) {
            this.enumType = enumType;
        }

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            setValue(Enum.valueOf(enumType, text.toUpperCase()));
        }
    }

}
