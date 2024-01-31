package backend_group_5.we_lead_bootcamp.controller;

import backend_group_5.we_lead_bootcamp.mapper.AddressMapper;
import backend_group_5.we_lead_bootcamp.mapper.BaseMapper;
import backend_group_5.we_lead_bootcamp.mapper.StoreMapper;
import backend_group_5.we_lead_bootcamp.mapper.UserMapper;
import backend_group_5.we_lead_bootcamp.model.User;
import backend_group_5.we_lead_bootcamp.repository.AddressRepository;
import backend_group_5.we_lead_bootcamp.service.BaseService;
import backend_group_5.we_lead_bootcamp.service.UserService;
import backend_group_5.we_lead_bootcamp.transfer.ApiResponse;
import backend_group_5.we_lead_bootcamp.transfer.resource.AddressResource;
import backend_group_5.we_lead_bootcamp.transfer.resource.UserResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("users")
@RequiredArgsConstructor

public class UserController extends BaseController<User, UserResource> {
    private final UserService userService;
    private  final UserMapper userMapper;
    private final StoreMapper storeMapper;
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;



    @Override
    protected BaseService<User, Long> getBaseService() {
        return userService;
    }

    @Override
    protected BaseMapper<User, UserResource> getMapper() {
        return userMapper;
    }


    @Override
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResource>>> findAll(){
        return ResponseEntity.ok(
                ApiResponse.<List<UserResource>>builder()
                        .data(userMapper.toLightResources( userService.findAll()))
                        .build());
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<UserResource>> get(@PathVariable("id") final Long id){
        return ResponseEntity.ok(
                ApiResponse.<UserResource>builder()
                        .data(userMapper.toLightResource(userService.getById(id)))
                        .build());
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserResource>> createAccount(@RequestBody UserResource userResource) {

        var user = getMapper().toDomain(userResource);


        user.setPaymentMethod(userMapper.toDomainPaymentMethod(userResource.getPaymentMethod()));
        System.out.println(user.getPaymentMethod());

        return new ResponseEntity<>(ApiResponse.<UserResource>builder()
                .data(userMapper.toLightResource(userService.createAccount(user)))
                .build(),
                getNoCacheHeaders(),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResource>> login(@RequestParam String username,@RequestParam String password) {

        return new ResponseEntity<>(ApiResponse.<UserResource>builder()
                .data(userMapper.toLightResource(userService.logIn(username,password)))
                .build(),
                getNoCacheHeaders(),
                HttpStatus.CREATED);

    }




    @PutMapping("/update/phone")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePhone(@RequestBody UserResource userResource){
        User user = getMapper().toDomain(userResource);
        String email = user.getEmail();
        String phone = user.getPhone();
        userService.updatePhone(email,phone);
    }
    @PutMapping("/update/email")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmail(@RequestBody UserResource userResource,@RequestParam String new_email){
        var user = userMapper.toDomain(userResource);
        String email = user.getEmail();
        String password = user.getPassword();

        System.out.println(password);
        userService.updateEmail(email,new_email,password);

    }
    @PutMapping("/update/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@RequestBody UserResource userResource,@RequestParam String new_password){
        var user = userMapper.toDomain(userResource);
        String email = user.getEmail();
        String password = user.getPassword();

        userService.updatePassword(email,password,new_password);

    }
    @PutMapping("/update/favourite")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFavouriteStore(@RequestBody String email, String storeName){

        var user = userService.findByEmail(email);
        userService.updateFavouriteStores(user,storeName);
    }
    @PutMapping("/update/address")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAddress(@RequestParam String email,@RequestBody  AddressResource addressResource){

        User user = userService.findByEmail(email);
        var address = addressMapper.toDomain(addressResource);
        userService.updateAddress(user.getId(),address);


    }
    @PostMapping("/get_user_addresses")
    public List<AddressResource> getUserAddresses(@RequestParam String email){

        var user = userService.findByEmail(email);
        Long id = user.getId();


        System.out.println(id);


        return addressMapper.toResources(userService.getUserAddressList(id));




    }
    @GetMapping("/update/my-orderHistory")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getOrderHistory(@RequestBody UserResource userResource){
        var user = userMapper.toDomain(userResource);
        Long id = user.getId();
        System.out.println((userService.getOrderHistory(id)));


    }

    @DeleteMapping("/deleteAccount")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam String email) {

        System.out.println(email);
         userService.deleteAccount(email);

    }




}