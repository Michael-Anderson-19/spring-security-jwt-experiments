package io.github.MichaelAnderson19.ManualLogin.service.security;

import io.github.MichaelAnderson19.ManualLogin.model.AppUser;
import io.github.MichaelAnderson19.ManualLogin.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    public SecurityUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AppUser foundUser = appUserRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User with email %s not found".formatted(email)));

        return User.withUsername(foundUser.getEmail())
                .password(foundUser.getPassword())
                .authorities(List.of(
                        new SimpleGrantedAuthority("USER")))
                .build();
    }
}
