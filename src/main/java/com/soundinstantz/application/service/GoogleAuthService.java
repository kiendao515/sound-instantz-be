package com.soundinstantz.application.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.soundinstantz.application.dto.auth.AuthResponseDTO;
import com.soundinstantz.application.dto.auth.GoogleTokenDTO;
import com.soundinstantz.application.exception.AuthenticationException;
import com.soundinstantz.application.exception.BizException;
import com.soundinstantz.application.exception.TokenRefreshException;
import com.soundinstantz.domain.user.AuthProvider;
import com.soundinstantz.domain.user.User;
import com.soundinstantz.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleAuthService {

    @Value("${google.oauth.client-id}")
    private String clientId;

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Transactional
    public AuthResponseDTO authenticateWithGoogle(String idTokenString) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                    .setAudience(Collections.singletonList(clientId))
                    .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken == null) {
                throw new AuthenticationException("Invalid Google ID token");
            }

            Payload payload = idToken.getPayload();
            boolean emailVerified = payload.getEmailVerified();

            if (!emailVerified) {
                throw new AuthenticationException("Email not verified by Google");
            }

            User user = getOrCreateUser(payload);

            String accessToken = jwtService.generateAccessToken(user.getEmail());
            String refreshToken = jwtService.generateRefreshToken(user.getEmail());

            return AuthResponseDTO.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .expiresIn(3600) // 1 hour expiration
                    .build();

        } catch (GeneralSecurityException | IOException e) {
            log.error("Error verifying Google token", e);
            throw new AuthenticationException("Error verifying Google token");
        }
    }

    private User getOrCreateUser(Payload payload) {
        String email = payload.getEmail();
        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isPresent()) {
            return existingUser.get();
        }

        User newUser = User.builder()
                .email(email)
                .name((String) payload.get("name"))
                .imageUrl((String) payload.get("picture"))
                .provider(AuthProvider.GOOGLE)
                .build();

        return userRepository.save(newUser);
    }

    public AuthResponseDTO refreshToken(String refreshToken) {
        try {
            String email = jwtService.verifyToken(refreshToken);
            if (email == null) {
                throw new TokenRefreshException("Invalid refresh token");
            }

            String newAccessToken = jwtService.generateAccessToken(email);
            String newRefreshToken = jwtService.generateRefreshToken(email);

            return AuthResponseDTO.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(newRefreshToken)
                    .expiresIn(3600) // 1 hour
                    .build();
        } catch (Exception e) {
            throw new TokenRefreshException("Error refreshing token: " + e.getMessage());
        }
    }
}
