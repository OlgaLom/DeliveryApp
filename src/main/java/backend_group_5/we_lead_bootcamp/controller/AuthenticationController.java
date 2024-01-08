package backend_group_5.we_lead_bootcamp.controller;

import backend_group_5.we_lead_bootcamp.model.User;
import backend_group_5.we_lead_bootcamp.service.UserService;
import backend_group_5.we_lead_bootcamp.transfer.ApiResponse;
import backend_group_5.we_lead_bootcamp.transfer.resource.UserResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserResource>> signUp(@RequestBody User user){
        return ResponseEntity.ok(ApiResponse.<UserResource>builder().build());
    }

}
