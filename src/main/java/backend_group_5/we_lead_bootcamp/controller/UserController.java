package backend_group_5.we_lead_bootcamp.controller;
import backend_group_5.we_lead_bootcamp.mapper.BaseMapper;
import backend_group_5.we_lead_bootcamp.mapper.UserMapper;
import backend_group_5.we_lead_bootcamp.model.User;
import backend_group_5.we_lead_bootcamp.service.BaseService;
import backend_group_5.we_lead_bootcamp.service.UserService;
import backend_group_5.we_lead_bootcamp.transfer.ApiResponse;
import backend_group_5.we_lead_bootcamp.transfer.resource.UserResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor

public class UserController extends BaseController<User,UserResource> {
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

    @RequestMapping("users/createAccount")
    public User createAccount(@RequestBody User user){
        return userService.createAccount(user);
    }
}

