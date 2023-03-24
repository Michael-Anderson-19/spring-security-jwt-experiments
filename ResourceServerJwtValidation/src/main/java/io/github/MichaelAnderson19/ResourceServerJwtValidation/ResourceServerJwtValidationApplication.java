package io.github.MichaelAnderson19.ResourceServerJwtValidation;

import io.github.MichaelAnderson19.ResourceServerJwtValidation.model.AppUser;
import io.github.MichaelAnderson19.ResourceServerJwtValidation.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@SpringBootApplication
public class ResourceServerJwtValidationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceServerJwtValidationApplication.class, args);
	}


	@Bean
	CommandLineRunner initUser(UserRepository userRepository, PasswordEncoder encoder) {
		return args -> {
			String email = "michael@test.com";
			if(!userRepository.existsByEmail(email))
			{
				 userRepository.save(
						AppUser.builder()
								.email("michael@test.com")
								.password(encoder.encode("12345"))
								.firstName("michael").lastName("anderson")
								.publicId(UUID.randomUUID().toString())
								.build()
				);
				System.out.println("\nuser created\n");
			}
		};
	}
}
