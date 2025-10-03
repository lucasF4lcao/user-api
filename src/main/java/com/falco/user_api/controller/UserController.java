package com.falco.user_api.controller;

import com.falco.user_api.dto.UserRequest;
import com.falco.user_api.dto.UserResponse;
import com.falco.user_api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        UserResponse response = userService.createUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable Long id) {
        UserResponse response = userService.getUserProfile(id);
        return ResponseEntity.ok(response);
    }
}
