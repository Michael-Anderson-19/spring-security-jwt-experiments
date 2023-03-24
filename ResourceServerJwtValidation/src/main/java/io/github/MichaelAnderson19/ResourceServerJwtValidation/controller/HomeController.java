package io.github.MichaelAnderson19.ResourceServerJwtValidation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping(value={"","/"})
    public String index() {
        return "welcome all";
    }

    @GetMapping(value={"/protected","/protected/"})
    public String protectedRoute(Principal principal) {
        return "welcome to the protected endpoint %s".formatted(principal.getName());
    }
}
