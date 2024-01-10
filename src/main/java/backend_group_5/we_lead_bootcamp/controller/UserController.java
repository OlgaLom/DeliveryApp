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
    private final UserMapper userMapper;

    @Override
    protected BaseService<User, Long> getBaseService() {
        return userService;
    }

    @Override
    protected BaseMapper<User, UserResource> getMapper() {
        return userMapper;
    }
   /* @Override
    protected BaseService<User, Long> getBaseService() {
        return userService;
    }

    @Override
    protected BaseMapper<User, UserResource> getMapper() {
        return userMapper;
    }*/
   @ResponseBody
   @RequestMapping(value = "hello",method = RequestMethod.GET)
   public String hello(){
       return "hello world";
   }
   @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserResource>> createAccount(@RequestBody final UserResource  userResource){
         var user = getBaseService().create(getMapper().toDomain(userResource));
         return new ResponseEntity<>(ApiResponse.<UserResource>builder()
                 .data(getMapper().toResource(userService.createAccount(user)))
                 .build(),
                getNoCacheHeaders(),
                 HttpStatus.CREATED);
     }


}

