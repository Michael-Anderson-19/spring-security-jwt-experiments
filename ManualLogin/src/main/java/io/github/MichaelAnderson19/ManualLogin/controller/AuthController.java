package io.github.MichaelAnderson19.ManualLogin.controller;

import io.github.MichaelAnderson19.ManualLogin.dto.request.LoginRequestModel;
import io.github.MichaelAnderson19.ManualLogin.dto.response.LoginResponseModel;
import io.github.MichaelAnderson19.ManualLogin.service.security.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseModel> loginUser(@RequestBody LoginRequestModel loginRequest) {
        return new ResponseEntity(
                authService.loginUser(loginRequest),
                HttpStatus.OK);
    }
}
