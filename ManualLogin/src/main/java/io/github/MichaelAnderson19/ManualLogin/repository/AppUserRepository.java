package io.github.MichaelAnderson19.ManualLogin.repository;

import io.github.MichaelAnderson19.ManualLogin.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

}
