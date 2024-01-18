package backend_group_5.we_lead_bootcamp.controller;

import backend_group_5.we_lead_bootcamp.mapper.*;
import backend_group_5.we_lead_bootcamp.model.*;
import backend_group_5.we_lead_bootcamp.service.*;
import backend_group_5.we_lead_bootcamp.transfer.ApiResponse;
import backend_group_5.we_lead_bootcamp.transfer.resource.OrderResource;
import backend_group_5.we_lead_bootcamp.transfer.resource.ProductResource;
import backend_group_5.we_lead_bootcamp.transfer.resource.StoreResource;
import backend_group_5.we_lead_bootcamp.transfer.resource.UserResource;
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

    private final ProductService productService;
    private final ProductMapper productMapper;

    private final UserService userService;
    private final UserMapper userMapper;

    private final StoreService storeService;
    private final StoreMapper storeMapper;


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
    public ResponseEntity<ApiResponse<OrderResource>> createOrder(@RequestParam UserResource userR, @RequestParam StoreResource storeR) {
        var user = userMapper.toDomain(userR);
        var store = storeMapper.toDomain(storeR);
        return ResponseEntity.ok(
                ApiResponse.<OrderResource>builder()
                        .data(orderMapper.toResource(orderService.initiateOrder(user,store)))
                        .build());
    }

    //Add item
    @PostMapping(params = {"order","product","quantity"})
    @RequestMapping("additem")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addItem(@RequestParam OrderResource orderR, @RequestParam ProductResource productR, @RequestParam int quantity){

            var ord = orderMapper.toDomain(orderR);
            var prod = productMapper.toDomain(productR);

            orderService.addItem(ord, prod, quantity);

    }

    //Update item
    @PutMapping(params = {"order","product","quantity"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping("updateitem")
    public void updateItem(@RequestParam OrderResource orderR, @RequestParam ProductResource productR, @RequestParam int quantity){
        //SHOULD I CONVERT OBJECTS TO RESOURCES?
        var ord = orderMapper.toDomain(orderR);
        var prod = productMapper.toDomain(productR);

        orderService.updateItem(ord,prod,quantity);
    }
    //Delete item - NOT SURE
    @DeleteMapping(params = {"order","product"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping("removeitem")
    public void removeItem(@RequestParam OrderResource orderR, @RequestParam ProductResource productR){
        var ord = orderMapper.toDomain(orderR);
        var prod = productMapper.toDomain(productR);

        orderService.removeItem(ord,prod);
    }

    //Finalize Order
    @PostMapping(params = {"order", "paymentMethod","address","orderNote"})
    @RequestMapping("create")
    public ResponseEntity<ApiResponse<OrderResource>> finalizeOrder(
            @RequestParam OrderResource orderR,
            @RequestParam PaymentMethod paymentMethod,
            @RequestParam Address address,
            @RequestParam String orderNote) {

        var ord = orderMapper.toDomain(orderR);

        return ResponseEntity.ok(
                ApiResponse.<OrderResource>builder()
                        .data(orderMapper.toResource(orderService.finalizeOrder(ord,paymentMethod,address,orderNote)))
                        .build());
    }





}
