package backend_group_5.we_lead_bootcamp.controller;

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
        var user = userMapper.toDomain(userResource);
        System.out.println(user);
        System.out.println("THIS IS THE ROLE");
        System.out.println(user.getRole());
        return new ResponseEntity<>(ApiResponse.<UserResource>builder()
                .data(userMapper.toResource(userService.createAccount(user)))
                .build(),
                //getNoCacheHeaders(),
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




}