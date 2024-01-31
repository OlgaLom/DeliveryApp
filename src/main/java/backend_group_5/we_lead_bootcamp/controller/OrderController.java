package backend_group_5.we_lead_bootcamp.controller;

import backend_group_5.we_lead_bootcamp.mapper.*;
import backend_group_5.we_lead_bootcamp.model.*;
import backend_group_5.we_lead_bootcamp.model.enums.OrderStatus;
import backend_group_5.we_lead_bootcamp.model.enums.PaymentMethod;
import backend_group_5.we_lead_bootcamp.service.*;
import backend_group_5.we_lead_bootcamp.transfer.ApiResponse;
import backend_group_5.we_lead_bootcamp.transfer.KeyValue;
import backend_group_5.we_lead_bootcamp.transfer.OrderByOrderNumber;
import backend_group_5.we_lead_bootcamp.transfer.resource.*;
import jakarta.persistence.EnumType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static java.lang.Long.parseLong;

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

    private final  StoreService storeService;
    private final StoreMapper storeMapper;

    @Override
    protected BaseService<Order,Long> getBaseService(){return orderService;}

    @Override
    protected BaseMapper<Order, OrderResource> getMapper(){return orderMapper;}


    //Get all orders
    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResource>>> findAll(){
        return ResponseEntity.ok(
                ApiResponse.<List<OrderResource>>builder()
                        .data( orderMapper.toLightResources( orderService.findAll()) )
                        .build());
    }
    //Get by id
    @Override
    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<OrderResource>> get(@PathVariable("id") final Long id){
        return ResponseEntity.ok(
                ApiResponse.<OrderResource>builder()
                        .data(orderMapper.toLightResource(orderService.getById(id)))
                        .build());
    }

    // Get order by order number
//    @GetMapping("/orderNumber/{orderNumber}")
//    public ResponseEntity<ApiResponse<OrderResource>> getOrderByOrderNumber(@PathVariable("orderNumber") final String ordNumber){
//        return ResponseEntity.ok(
//                ApiResponse.<OrderResource>builder()
//                        .data(orderMapper.toResource(orderService.findByOrderNumber(ordNumber)))
//                        .build());
//    }
    @GetMapping("/orderNumber/{orderNumber}")
    public ResponseEntity<ApiResponse<OrderByOrderNumber<String,BigDecimal, EnumType, Date,EnumType,String,String,String,String>>> getOrderByOrderNumber(@PathVariable("orderNumber") final String ordNumber){
         return ResponseEntity.ok(
                ApiResponse.<OrderByOrderNumber<String,BigDecimal,EnumType, Date,EnumType,String,String,String,String>>builder()
                        .data( orderService.findByOrderNumber(ordNumber) )
                        .build());
    }

    //Initiate Order
    @PostMapping(path = "/initialize",params = {"UserId","StoreId"})
    public ResponseEntity<ApiResponse<OrderResource>> createOrder(
            @RequestParam("UserId") final Long UserId,
            @RequestParam("StoreId") final Long StoreId) {

        UserResource userR = userMapper.toResource(userService.getById(UserId) );
        User user = userMapper.toDomain( userR);

        StoreResource storeR = storeMapper.toResource(storeService.getById(StoreId) );
        Store store = storeMapper.toDomain(storeR);

        return ResponseEntity.ok(
                ApiResponse.<OrderResource>builder()
                        .data(orderMapper.toResource(orderService.initiateOrder(user,store)))
                        .build());
    }

    //Add item - NOT FINISHED
    @PostMapping("/items/add")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addItem(@RequestBody final OrderResource orderR, final ProductResource productR, final int quantity){

            var ord = orderMapper.toDomain(orderR);
            var prod = productMapper.toDomain( productR ); ;

            orderService.addItem(ord, prod, quantity);
    }

    //Update item - NOT FINISHED
    @PutMapping("/items/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateItem(@RequestBody final OrderResource orderR, @RequestBody final ProductResource productR, @RequestBody final int quantity){
        var ord = orderMapper.toDomain(orderR);
        var prod = productMapper.toDomain(productR);

        orderService.updateItem(ord,prod,quantity);
    }
    // Delete an Item - NOT FINISHED
    @DeleteMapping("/items/remove")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeItem(@RequestBody final OrderResource orderR, @RequestBody final ProductResource productR){
        var ord = orderMapper.toDomain(orderR);
        var prod = productMapper.toDomain(productR);

        orderService.removeItem(ord,prod);
    }

    //Finalize Order - NOT FINISHED
    @PostMapping("/finalize")
    public ResponseEntity<ApiResponse<OrderResource>> finalizeOrder(
            @RequestBody final OrderResource orderR,
            @RequestBody final PaymentMethod paymentMethod,
            @RequestBody final OrderAddress address,
            @RequestBody final String orderNote) {

        var ord = orderMapper.toDomain(orderR);

        return ResponseEntity.ok(
                ApiResponse.<OrderResource>builder()
                        .data(orderMapper.toResource(orderService.finalizeOrder(ord,paymentMethod,address,orderNote)))
                        .build());
    }


    //Get All orders of a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<OrderResource>>> getOrdersByUser(
            @PathVariable("userId") final Long userId) {
        List<Order> userOrders = orderService.findOrdersByUser(userId);

        return ResponseEntity.ok(
                ApiResponse.<List<OrderResource>>builder()
                        .data(orderMapper.toLightResources(userOrders))
                        .build());
    }
    @GetMapping("/store/{storeId}")
    public ResponseEntity<ApiResponse<List<OrderResource>>> getOrdersByStore(@PathVariable("storeId") final Long storeId) {
        List<Order> storeOrders = orderService.findOrdersByStore(storeId);

        return ResponseEntity.ok(
                ApiResponse.<List<OrderResource>>builder()
                        .data(orderMapper.toLightResources(storeOrders))
                        .build());
    }

    //Get orders by date
    @GetMapping(value = "/date/{orderDate}")
    public ResponseEntity<ApiResponse<List<OrderResource>>> getOrdersByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "d-M-yyyy") LocalDate orderDate) {
        List<Order> ordersByDate = orderService.findOrdersByDate(orderDate);

        return ResponseEntity.ok(
                ApiResponse.<List<OrderResource>>builder()
                        .data(orderMapper.toLightResources(ordersByDate))
                        .build());
    }
    //Get orders by status
    @GetMapping("/status/{orderStatus}")
    public ResponseEntity<ApiResponse<List<OrderResource>>> findOrdersByOrderStatus(
            @PathVariable OrderStatus orderStatus) {

        List<Order> ordersByStatus = orderService.findOrdersByOrderStatus(orderStatus);

        return ResponseEntity.ok(
                ApiResponse.<List<OrderResource>>builder()
                        .data(orderMapper.toLightResources(ordersByStatus))
                        .build());
    }


    //Get orders by a range of dates
    @GetMapping(value = "/date-range",params = {"fromDate","untilDate"})
    public ResponseEntity<ApiResponse<List<OrderResource>>> findOrdersDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "d-M-yyyy") final LocalDate fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "d-M-yyyy") final LocalDate untilDate) {
        List<Order> ordersByDateRange = orderService.findOrdersDateRange(fromDate,untilDate);

        return ResponseEntity.ok(
                ApiResponse.<List<OrderResource>>builder()
                        .data(orderMapper.toLightResources(ordersByDateRange))
                        .build());
    }

    //Get orders by a range of dates
    @GetMapping(value = "/date-range-above-total",params = {"fromDate","untilDate","orderTotal"})
    public ResponseEntity<ApiResponse<List<OrderResource>>> findOrdersByDateRangeAndAboveTotal(
            @RequestParam @DateTimeFormat(pattern = "d-M-yyyy") final LocalDate fromDate,
            @RequestParam @DateTimeFormat(pattern = "d-M-yyyy") final LocalDate untilDate,
            @RequestParam final BigDecimal orderTotal) {
        List<Order> ordersByDateRangeAboveTotal = orderService.findOrdersByDateRangeAndAboveTotal(fromDate,untilDate,orderTotal);

        return ResponseEntity.ok(
                ApiResponse.<List<OrderResource>>builder()
                        .data(orderMapper.toLightResources(ordersByDateRangeAboveTotal))
                        .build());
    }
    // Get orders by ranged date and below total
    @GetMapping(value = "/date-range-below-total", params = {"fromDate","untilDate","orderTotal"})
    public ResponseEntity<ApiResponse<List<OrderResource>>> findOrdersByDateRangeAndBelowTotal(
            @RequestParam @DateTimeFormat(pattern = "d-M-yyyy") final LocalDate fromDate,
            @RequestParam @DateTimeFormat(pattern = "d-M-yyyy") final LocalDate untilDate,
            @RequestParam final BigDecimal orderTotal) {
        List<Order> ordersByDateRangeBelowTotal = orderService.findOrdersByDateRangeAndBelowTotal(fromDate,untilDate,orderTotal);

        return ResponseEntity.ok(
                ApiResponse.<List<OrderResource>>builder()
                        .data(orderMapper.toLightResources(ordersByDateRangeBelowTotal))
                        .build());
    }
    // Get orders and above total
    @GetMapping("/above-total/{orderTotal}")
    public ResponseEntity<ApiResponse<List<OrderResource>>> findOrdersByAboveTotal(
            @PathVariable final BigDecimal orderTotal) {
        List<Order> ordersByAboveTotal = orderService.findOrdersByAboveTotal(orderTotal);

        return ResponseEntity.ok(
                ApiResponse.<List<OrderResource>>builder()
                        .data(orderMapper.toLightResources(ordersByAboveTotal))
                        .build());
    }

    @GetMapping("/below-total/{orderTotal}")
    public ResponseEntity<ApiResponse<List<OrderResource>>> findOrdersByBelowTotal(
            @PathVariable final BigDecimal orderTotal) {
        List<Order> ordersByBelowTotal = orderService.findOrdersByBelowTotal(orderTotal);

        return ResponseEntity.ok(
                ApiResponse.<List<OrderResource>>builder()
                        .data(orderMapper.toLightResources(ordersByBelowTotal))
                        .build());
    }
    // Get orders by order item name
    @GetMapping(value = "/item", params = {"itemName"})
    public ResponseEntity<ApiResponse<List<OrderResource>>> findOrdersByOrderItem(
            @RequestParam final String itemName) {
        List<Order> ordersByItemsName = orderService.findOrdersByOrderItem(itemName);

        return ResponseEntity.ok(
                ApiResponse.<List<OrderResource>>builder()
                        .data(orderMapper.toLightResources(ordersByItemsName))
                        .build());
    }
    // Get orders by address info [ Partially match ]
    @GetMapping("/address")
    public ResponseEntity<ApiResponse<List<OrderResource>>> findOrdersByAddress(
            @RequestParam(required = false) final String address,
            @RequestParam(required = false) final Integer streetNumber,
            @RequestParam(required = false) final String city ) {

        List<Order> ordersByAddress = orderService.findOrdersByAddress(address,streetNumber,city);

        return ResponseEntity.ok(
                ApiResponse.<List<OrderResource>>builder()
                        .data(orderMapper.toLightResources(ordersByAddress))
                        .build());
    }
    // Get stores revenues
   @GetMapping("/stores-revenues")
    public ResponseEntity<ApiResponse<List<KeyValue<String,BigDecimal>>>>findOrdersByStoresRevenues() {

       List<KeyValue<String, BigDecimal>> ordersByStoresRevenues = orderService.findOrdersByStoresRevenues();

        return ResponseEntity.ok(
                ApiResponse.<List<KeyValue<String, BigDecimal>>>builder()
                        .data(ordersByStoresRevenues)
                        .build());
    }

//    @GetMapping("/orderwithusers")
//    public ResponseEntity<ApiResponse<List<OrderAndUserResource>>>findAllOrderWithUserData() {
//
//        return ResponseEntity.ok(
//                ApiResponse.<List<OrderAndUserResource>>builder()
//                        .data(orderMapper.toResources(orderService.findAllOrderWithUserData()))
//                        .build());
//    }



}
