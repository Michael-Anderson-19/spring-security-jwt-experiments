package io.github.MichaelAnderson19.ManualLogin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/welcome")
    public ResponseEntity<String> welcomeAll() {
        return new ResponseEntity<>("Welcome all!, to the unprotected endpoint", HttpStatus.OK);
    }

    @GetMapping("/protected")
    public ResponseEntity<String> welcomeAll(Principal principal) {
        return new ResponseEntity<>("Welcome %s, to the protected endpoint, you must be logged in to see thius message".formatted(principal.getName()),
                HttpStatus.OK);
    }
}
