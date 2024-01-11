package backend_group_5.we_lead_bootcamp.controller;

import backend_group_5.we_lead_bootcamp.mapper.BaseMapper;
import backend_group_5.we_lead_bootcamp.mapper.OrderMapper;
import backend_group_5.we_lead_bootcamp.model.*;
import backend_group_5.we_lead_bootcamp.service.BaseService;
import backend_group_5.we_lead_bootcamp.service.OrderService;
import backend_group_5.we_lead_bootcamp.transfer.ApiResponse;
import backend_group_5.we_lead_bootcamp.transfer.resource.OrderResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController:
// → With this annotation we define this class responsibly for handling incoming request and returning responses
// → The return values of the methods there are the response body
@RestController
// @RequestMapping:
// → Defines that all methods within this controller will handle requests that are associate with orders
@RequestMapping("orders")
// @RequiredArgsConstructor:
// → This annotation is part of the Lombok library.
// → this annotation generates a constructor for the class and initialize all the fields. (This is especially useful in Spring components, such as controllers and services, where you often want to inject dependencies through constructor injection and have them be immutable (marked as final))
@RequiredArgsConstructor
public class OrderController extends BaseController<Order, OrderResource>{
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Override
    protected BaseService<Order,Long> getBaseService(){return orderService;}

    @Override
    protected BaseMapper<Order, OrderResource> getMapper(){return orderMapper;}

    // Get order by order number
    @GetMapping(params = {"orderNumber"})
    public ResponseEntity<ApiResponse<OrderResource>> get(@RequestParam("orderNumber") final String ordNumber){
        return ResponseEntity.ok(
                ApiResponse.<OrderResource>builder()
                        .data(orderMapper.toResource(orderService.findByOrderNumber(ordNumber)))
                        .build());
    }

    //Get all orders
    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResource>>> findAll(){
        return ResponseEntity.ok(
                ApiResponse.<List<OrderResource>>builder()
                        .data(orderMapper.toResources(orderService.findAll()))
                        .build());
    }

    //Initiate Order
    @PostMapping(params = {"user", "store"})
    @RequestMapping("create")
    public ResponseEntity<ApiResponse<OrderResource>> createOrder(@RequestParam User user, @RequestParam Store store) {
        return ResponseEntity.ok(
                ApiResponse.<OrderResource>builder()
                        .data(orderMapper.toResource(orderService.initiateOrder(user,store)))
                        .build());
    }

    //Add item
    @PostMapping(params = {"order","product","quantity"})
    @RequestMapping("additem")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addItem( @RequestParam Order order, @RequestParam Product product, @RequestParam int quantity){
        orderService.addItem(order, product, quantity);
    }

    //Update item
    @PutMapping(params = {"order","product","quantity"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping("updateitem")
    public void updateItem(@RequestParam Order order, @RequestParam Product product, @RequestParam int quantity){
        orderService.updateItem(order,product,quantity);
    }
    //Delete item - NOT SURE
    @DeleteMapping(params = {"order","product"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping("removeitem")
    public void removeItem(@RequestParam Order order, @RequestParam Product product){
        //SHOULD I CONVERT OBJECTS TO RESOURCES?
        orderService.removeItem(order,product);
    }

    //Finalize Order
    @PostMapping(params = {"order", "paymentMethod","address","orderNote"})
    @RequestMapping("create")
    public ResponseEntity<ApiResponse<OrderResource>> finalizeOrder(
            @RequestParam Order order,
            @RequestParam PaymentMethod paymentMethod,
            @RequestParam Address address,
            @RequestParam String orderNote) {
        return ResponseEntity.ok(
                ApiResponse.<OrderResource>builder()
                        .data(orderMapper.toResource(orderService.finalizeOrder(order,paymentMethod,address,orderNote)))
                        .build());
    }





}
