package io.github.MichaelAnderson19.ManualLogin;

import io.github.MichaelAnderson19.ManualLogin.model.AppUser;
import io.github.MichaelAnderson19.ManualLogin.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@SpringBootApplication
public class ManualLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManualLoginApplication.class, args);
	}

	@Bean
	public CommandLineRunner initUser(AppUserRepository userRepository, PasswordEncoder encoder) {
		return args -> {
			userRepository.save(
					AppUser.builder()
							.firstName("michael")
							.lastName("anderson")
							.email("michael@test.com")
							.publicId(UUID.randomUUID().toString())
							.password(encoder.encode("12345"))
							.build()
			);
		};
	}

}
