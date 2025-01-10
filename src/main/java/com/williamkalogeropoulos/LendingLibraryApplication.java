package com.williamkalogeropoulos;

import com.williamkalogeropoulos.entity.Role;
import com.williamkalogeropoulos.entity.User;
import com.williamkalogeropoulos.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableScheduling  // ✅ Enables scheduled tasks
public class LendingLibraryApplication {
    public static void main(String[] args) {
        SpringApplication.run(LendingLibraryApplication.class, args);
    }

    @Bean
    public CommandLineRunner createDefaultAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123")); // ✅ Ensure password is hashed
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
                System.out.println("✅ Admin user created: admin / admin123");
            } else {
                System.out.println("✅ Admin user already exists");
            }
            if (userRepository.findByUsername("user").isEmpty()) {
                User user = new User();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("User12345!")); // ✅ Ensure password is hashed
                user.setRole(Role.USER);
                userRepository.save(user);
                System.out.println("✅ User user created: user / User12345! ");
            } else {
                System.out.println("✅ User user already exists");
            }

        };
    }
}