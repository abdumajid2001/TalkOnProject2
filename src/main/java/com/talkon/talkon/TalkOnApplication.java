package com.talkon.talkon;

import com.talkon.talkon.properties.ServerProperties;
import com.talkon.talkon.repositories.user.user.UserRepository;
import com.talkon.talkon.properties.OpenApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties({
        OpenApiProperties.class,
        ServerProperties.class
})
@RequiredArgsConstructor
public class TalkOnApplication {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(TalkOnApplication.class, args);
    }


}
