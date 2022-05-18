package com.talkon.talkon.talkon;

import com.talkon.talkon.talkon.entities.user.User;
import com.talkon.talkon.talkon.enums.Role;
import com.talkon.talkon.talkon.properties.OpenApiProperties;
import com.talkon.talkon.talkon.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.TimeZone;
import java.util.UUID;

@SpringBootApplication
@EnableConfigurationProperties({
        OpenApiProperties.class
})
@RequiredArgsConstructor
public class TalkOnApplication {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(TalkOnApplication.class, args);
    }

    //    @Bean
    CommandLineRunner runner() {
        User user = User.childBuilder()
                .id(UUID.randomUUID().toString())
                .firstName("abdumajid")
                .lastName("abdullatipov")
                .phoneNumber("+998900032869")
                .email("abdumajidabdullatipov1@gmail.com")
                .username("admin")
                .password(passwordEncoder.encode("123"))
                .dataOfBirth(LocalDate.now())
                .role(Role.ADMIN)
                .timeZone(TimeZone.getDefault()).build();
        return (args -> {
            repository.save(user);
        });


    }

}
