package com.soundinstantz.interfaces.rest;

import com.soundinstantz.application.dto.auth.AuthResponseDTO;
import com.soundinstantz.application.dto.auth.GoogleTokenDTO;
import com.soundinstantz.application.dto.auth.RefreshTokenRequest;
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
    public ResponseEntity<AuthResponseDTO> googleAuth(@RequestBody GoogleTokenDTO tokenDTO) {
        AuthResponseDTO response = googleAuthService.authenticateWithGoogle(tokenDTO.getToken());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDTO> refreshToken(@RequestBody RefreshTokenRequest request) {
        AuthResponseDTO response = googleAuthService.refreshToken(request.getRefreshToken());
        return ResponseEntity.ok(response);
    }
}
