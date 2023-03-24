package io.github.MichaelAnderson19.ResourceServerJwtValidation.service;

import io.github.MichaelAnderson19.ResourceServerJwtValidation.model.AppUser;
import io.github.MichaelAnderson19.ResourceServerJwtValidation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser existingUser = userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("a user with the email address %s does not exist".formatted(email)));

        return User
                .withUsername(email)
                .password(existingUser.getPassword())
                .authorities(
                        List.of(new SimpleGrantedAuthority("USER"))
                ).build();
    }
}
