package io.github.MichaelAnderson19.ManualLogin.dto.response;

import lombok.Builder;

@Builder
public class LoginResponseModel {

    private String jwt;
    private String email;

    public LoginResponseModel() {
    }

    public LoginResponseModel(String jwt, String email) {
        this.jwt = jwt;
        this.email = email;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
