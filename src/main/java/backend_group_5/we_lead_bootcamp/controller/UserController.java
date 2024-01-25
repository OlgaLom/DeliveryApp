package backend_group_5.we_lead_bootcamp.controller;

import backend_group_5.we_lead_bootcamp.mapper.AddressMapper;
import backend_group_5.we_lead_bootcamp.mapper.BaseMapper;
import backend_group_5.we_lead_bootcamp.mapper.UserMapper;
import backend_group_5.we_lead_bootcamp.model.User;
import backend_group_5.we_lead_bootcamp.service.BaseService;
import backend_group_5.we_lead_bootcamp.service.UserService;
import backend_group_5.we_lead_bootcamp.transfer.ApiResponse;
import backend_group_5.we_lead_bootcamp.transfer.resource.UserResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController extends BaseController<User, UserResource> {
    private final UserService userService;
    private  final UserMapper userMapper;
    private final AddressMapper addressMapper;



    @Override
    protected BaseService<User, Long> getBaseService() {
        return userService;
    }

    @Override
    protected BaseMapper<User, UserResource> getMapper() {
        return userMapper;
    }


    @ResponseBody
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello() {
        return "hello world";
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserResource>> createAccount(@RequestBody UserResource userResource) {
        //ksexorista tis parametroys ena ena
        var user = getMapper().toDomain(userResource);
        user.setAddressList(userMapper.toDomainAddressList(userResource.getAddressList()));
        System.out.println(user.getAddressList());
        /*example request
        {
  "email": "user@example.com",
  "password": "secretpassword",
  "firstName": "John",
  "lastName": "Doe",
  "age": 25,
  "addressList": [
    {
        "address": "123 Main St",
        "streetNumber": 124,
        "city": "City1"

    }
  ],
  "phone": 1234567890,
  "city": "UserCity",
  "paymentMethod": "CREDIT_CARD",
  "role": "USER"
}
         */

        return new ResponseEntity<>(ApiResponse.<UserResource>builder()
                .data(userMapper.toResource(userService.createAccount(user)))
                .build(),
                getNoCacheHeaders(),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserResource userResource) {
        var loginRequest = userMapper.toDomain(userResource);
        String username = loginRequest.getEmail();
        return new ResponseEntity<>(ApiResponse.<UserResource>builder()
                .data(userMapper.toResource(userService.logIn(username)))
                .build(),
              //  getNoCacheHeaders(),
                HttpStatus.CREATED);

    }



    //   @PostMapping ( "/login")
    // public ResponseEntity<?> createAuthenticationToken(@RequestBody UserResource authenticationRequest) throws Exception {
    //  var user = getBaseService().create(getMapper().toDomain(authenticationRequest));
    //   authenticate(user.getUsername(), user.getPassword());


    //   final UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
    //  Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
    //-   authenticationManager.authenticate(
    //-         new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
    //    final User userDetails = userService.logIn(user);
    //  final String token = jwtUtil.generateToken(user);

    //  }
    @PutMapping("/update/phone{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePhone(@PathVariable("id") Long id,@RequestBody Integer phone){

        userService.updatePhone(id,phone);
    }
    @PutMapping("/update/email{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmail(@PathVariable("id") Long id,@RequestBody String email){

        userService.updateEmail(id,email);

    }

    @DeleteMapping("/deleteAccount")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestBody UserResource userResource) {
        User user = userMapper.toDomain(userResource);
        String email = user.getEmail();
        Long id = userService.deleteAccount(user);
        getBaseService().deleteById(id);
    }



}