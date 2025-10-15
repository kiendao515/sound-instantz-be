package com.soundinstantz.interfaces.rest;

import com.soundinstantz.application.dto.auth.AuthRequest;
import com.soundinstantz.application.service.AuthService;
import com.soundinstantz.config.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody AuthRequest request) {
        var user = authService.register(request);
        String token = jwtTokenProvider.generateToken(user);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("userId", user.getId().toString());
        response.put("email", user.getEmail());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest request) {
        var user = authService.authenticate(request);
        String token = jwtTokenProvider.generateToken(user);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("userId", user.getId().toString());
        response.put("email", user.getEmail());

        return ResponseEntity.ok(response);
    }
}
