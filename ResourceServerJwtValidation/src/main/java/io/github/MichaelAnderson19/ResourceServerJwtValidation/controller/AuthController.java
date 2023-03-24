package io.github.MichaelAnderson19.ResourceServerJwtValidation.controller;

import io.github.MichaelAnderson19.ResourceServerJwtValidation.dto.request.LoginRequest;
import io.github.MichaelAnderson19.ResourceServerJwtValidation.dto.response.LoginResponse;
import io.github.MichaelAnderson19.ResourceServerJwtValidation.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger((AuthController.class));

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path={"/login","/login/"})
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest){
        var response = authService.loginUser(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
