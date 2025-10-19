package com.soundinstantz.interfaces.rest;

import com.soundinstantz.application.dto.auth.GoogleTokenDTO;
import com.soundinstantz.application.service.GoogleAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final GoogleAuthService googleAuthService;

    @PostMapping("/google")
    public ResponseEntity<Map<String, String>> googleAuth(@RequestBody GoogleTokenDTO tokenDTO) {
        String token = googleAuthService.authenticateWithGoogle(tokenDTO.getToken());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}
