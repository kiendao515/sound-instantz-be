package com.soundinstantz.application.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.soundinstantz.application.dto.auth.GoogleTokenDTO;
import com.soundinstantz.application.exception.AuthenticationException;
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
    public String authenticateWithGoogle(String idTokenString) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                    .setAudience(Collections.singletonList(clientId))
                    .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken == null) {
                throw new AuthenticationException("Invalid Google ID token");
            }

            Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            boolean emailVerified = payload.getEmailVerified();

            if (!emailVerified) {
                throw new AuthenticationException("Email not verified by Google");
            }

            // Get or create user
            User user = getOrCreateUser(payload);

            // Generate JWT token using email
            return jwtService.generateToken(user.getEmail());

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
}
