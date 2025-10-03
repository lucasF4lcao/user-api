package com.falco.user_api.service;

import com.falco.user_api.dto.LoginRequest;
import com.falco.user_api.dto.JwtResponse;
import com.falco.user_api.model.User;
import com.falco.user_api.repository.UserRepository;
import com.falco.user_api.security.JwtUtil;
import com.falco.user_api.security.UserPrincipal;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthService(AuthenticationManager authenticationManager,
                       JwtUtil jwtUtil,
                       UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    public JwtResponse authenticate(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        String token = jwtUtil.generateToken(principal);
        return new JwtResponse(token, jwtUtilExpirationMs());
    }

    private long jwtUtilExpirationMs() {
        return Long.parseLong(System.getProperty("jwt.expiration-ms", "3600000"));
    }
}
