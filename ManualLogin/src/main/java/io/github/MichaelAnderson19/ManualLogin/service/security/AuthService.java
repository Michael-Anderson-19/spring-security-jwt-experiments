package io.github.MichaelAnderson19.ManualLogin.service.security;

import io.github.MichaelAnderson19.ManualLogin.dto.request.LoginRequestModel;
import io.github.MichaelAnderson19.ManualLogin.dto.response.LoginResponseModel;
import io.github.MichaelAnderson19.ManualLogin.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    public AuthService(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public LoginResponseModel loginUser(LoginRequestModel loginRequest){

        //delegate authentication to the authentication manager
        Authentication authentication = authenticateUser(loginRequest.getEmail(),loginRequest.getPassword());

        String jwt = jwtUtils.generateToken(loginRequest.getEmail());
        return LoginResponseModel.builder()
                .email(loginRequest.getEmail())
                .jwt(jwt)
                .build();
    }

    private Authentication authenticateUser(String email, String password) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
    }
}
