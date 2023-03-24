package io.github.MichaelAnderson19.ResourceServerJwtValidation.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import io.github.MichaelAnderson19.ResourceServerJwtValidation.dto.request.LoginRequest;
import io.github.MichaelAnderson19.ResourceServerJwtValidation.dto.response.LoginResponse;
import io.github.MichaelAnderson19.ResourceServerJwtValidation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * the authentication manager has registered an authentication provider that will look up the user based on the passed in email or username
 * this is implemented with our userdetailsservice class so no need to do this manually
 */
@Service
public class AuthService {

    private final TokenServiceImpl tokenService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    public AuthService(TokenServiceImpl tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse loginUser(LoginRequest loginRequest) {
       //throws error if login fails

            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = tokenService.generateJwt(auth);

        return LoginResponse.builder().jwt(jwt).email(loginRequest.getEmail()).build();

    }
}
