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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    private final ProductMapper productMapper;

    private final UserMapper userMapper;

    private final StoreMapper storeMapper;


    @Override
    protected BaseService<Order,Long> getBaseService(){return orderService;}

    @Override
    protected BaseMapper<Order, OrderResource> getMapper(){return orderMapper;}


    //Get all orders
    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResource>>> findAll(){
        return ResponseEntity.ok(
                ApiResponse.<List<OrderResource>>builder()
                        .data(orderMapper.toResources(orderService.findAll()))
                        .build());
    }

    // Get order by order number
    @GetMapping("{orderNumber}")
    public ResponseEntity<ApiResponse<OrderResource>> getOrderByOrderNumber(@PathVariable("orderNumber") final String ordNumber){
        return ResponseEntity.ok(
                ApiResponse.<OrderResource>builder()
                        .data(orderMapper.toResource(orderService.findByOrderNumber(ordNumber)))
                        .build());
    }

    //Initiate Order
    // @PostMapping(params = {"user", "store"}) // No need for params we will pass the data to the body
    @RequestMapping("initialize")
    @PostMapping
    public ResponseEntity<ApiResponse<OrderResource>> createOrder(@RequestBody final UserResource userR, @RequestBody final StoreResource storeR) {
        var user = userMapper.toDomain(userR);
        var store = storeMapper.toDomain(storeR);
        return ResponseEntity.ok(
                ApiResponse.<OrderResource>builder()
                        .data(orderMapper.toResource(orderService.initiateOrder(user,store)))
                        .build());
    }

    //Add item
    // @PostMapping(params = {"order","product","quantity"})
    @RequestMapping("/items/add")
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addItem(@RequestBody final OrderResource orderR, @RequestBody final ProductResource productR, @RequestBody final int quantity){

            var ord = orderMapper.toDomain(orderR);
            var prod = productMapper.toDomain(productR);

            orderService.addItem(ord, prod, quantity);
    }

    //Update item
//    @PutMapping(params = {"order","product","quantity"})
    @RequestMapping("/items/update")
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateItem(@RequestBody final OrderResource orderR, @RequestBody final ProductResource productR, @RequestBody final int quantity){
        var ord = orderMapper.toDomain(orderR);
        var prod = productMapper.toDomain(productR);

        orderService.updateItem(ord,prod,quantity);
    }
    //@DeleteMapping(params = {"order","product"})
    @RequestMapping("/items/remove")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeItem(@RequestBody final OrderResource orderR, @RequestBody final ProductResource productR){
        var ord = orderMapper.toDomain(orderR);
        var prod = productMapper.toDomain(productR);

        orderService.removeItem(ord,prod);
    }

    //Finalize Order
    // @PostMapping(params = {"order", "paymentMethod","address","orderNote"})
    @PostMapping
    @RequestMapping("finalize")
    public ResponseEntity<ApiResponse<OrderResource>> finalizeOrder(
            @RequestBody final OrderResource orderR,
            @RequestBody final PaymentMethod paymentMethod,
            @RequestBody final Address address,
            @RequestBody final String orderNote) {

        var ord = orderMapper.toDomain(orderR);

        return ResponseEntity.ok(
                ApiResponse.<OrderResource>builder()
                        .data(orderMapper.toResource(orderService.finalizeOrder(ord,paymentMethod,address,orderNote)))
                        .build());
    }


    //Get All orders of a user
//    @GetMapping("user/{userId}")
//    public ResponseEntity<ApiResponse<List<OrderResource>>> getOrdersByUser(@PathVariable("userId") final Long userId) {
//        // Assuming orderService has a method to find orders by user
//        List<Order> userOrders = orderService.findOrdersByUser(userId);
//
//        return ResponseEntity.ok(
//                ApiResponse.<List<OrderResource>>builder()
//                        .data(orderMapper.toResourceList(userOrders))
//                        .build());
//    }

    //Get order by date
    @GetMapping("date/{orderDate}")
    public ResponseEntity<ApiResponse<List<OrderResource>>> getOrdersByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate orderDate) {
        List<Order> ordersByDate = orderService.findOrdersByDate(orderDate);

        return ResponseEntity.ok(
                ApiResponse.<List<OrderResource>>builder()
                        .data(orderMapper.toResourceList(ordersByDate))
                        .build());
    }



}
