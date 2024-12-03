package com.test.COCONSULT.Controllers;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.test.COCONSULT.DTO.RoleName;
import com.test.COCONSULT.DTO.TokenDto;
import com.test.COCONSULT.Entity.User;
import com.test.COCONSULT.Entity.UserPrinciple;
import com.test.COCONSULT.JWT.JwtAuthTokenFilter;
import com.test.COCONSULT.JWT.JwtProvider;
import com.test.COCONSULT.JWT.JwtResponse;
import com.test.COCONSULT.Reposotories.UserRepository;
import com.test.COCONSULT.ServiceIMP.UserServiceIMP;
import com.test.COCONSULT.Services.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
public class OAuth2Controller {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    MailSenderService mailSending;
    @Autowired
    JwtAuthTokenFilter jwtAuthTokenFilter;
    @Autowired
    UserServiceIMP userServiceIMP;
    @Autowired
    UserRepository userRepository;
    @Value("${google.clientId}")
    String googleClientId;

    @Value("${secretPsw}")
    String secretPsw;

    @GetMapping("/oauth2/'success")
    public ResponseEntity<?> oauth2SuccessRedirect() {
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", "http://localhost:4200/oauth2-success");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/oauth2/failure")
    public ResponseEntity<?> oauth2FailureRedirect() {
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", "http://localhost:4200/oauth2-failure");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/google")
    public ResponseEntity<JwtResponse> google(@RequestBody TokenDto tokenDto) throws IOException {
        final NetHttpTransport transport = new NetHttpTransport();
        final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
        GoogleIdTokenVerifier.Builder verifier =
                new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
                        .setAudience(Collections.singletonList(googleClientId));
        final GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), tokenDto.getValue());
        final GoogleIdToken.Payload payload = googleIdToken.getPayload();

        // Check if the user already exists in your database
        Optional<User> existingUserOptional = userRepository.findByEmail(payload.getEmail());
        User user = new User();
        user.setEmail(payload.getEmail());
        //user.setUsername(payload.getEmail());
        if (existingUserOptional.isPresent()) {
            // User exists, generate and return JWT tokens
            User existingUser = existingUserOptional.get();
            JwtResponse tokenRes = generateTokens(existingUser);
            return ResponseEntity.ok(tokenRes);
        } else {
            userServiceIMP.registerUserViaGoogle(user, "ROLE_USER");
            User newUser = userRepository.findByEmail(payload.getEmail()).get();
            JwtResponse tokenRes = generateTokens(newUser);
            return ResponseEntity.ok(tokenRes);
        }
    }

    private JwtResponse generateTokens(User user) {
        UserPrinciple userPrinciple = UserPrinciple.build(user);

        // Generate JWT tokens for the user
        Authentication authentication = new UsernamePasswordAuthenticationToken(userPrinciple, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtProvider.generateAccessToken(authentication);
        String refreshToken = jwtProvider.generateRefreshToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtResponse tokenDto = new JwtResponse();
        tokenDto.setAuthorities(userDetails.getAuthorities());
        tokenDto.setAccessToken(accessToken);
        tokenDto.setRefreshToken(refreshToken);
        tokenDto.setId(user.getId());
        tokenDto.setUsername(user.getUsername());
        return tokenDto;
    }

}
