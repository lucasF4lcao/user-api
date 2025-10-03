package com.falco.user_api.controller;

import com.falco.user_api.dto.LoginRequest;
import com.falco.user_api.dto.JwtResponse;
import com.falco.user_api.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        JwtResponse jwt = authService.authenticate(request);
        return ResponseEntity.ok(jwt);
    }
}
