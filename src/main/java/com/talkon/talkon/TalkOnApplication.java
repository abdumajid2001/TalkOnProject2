package com.talkon.talkon;

import com.talkon.talkon.entities.user.User;
import com.talkon.talkon.properties.OpenApiProperties;
import com.talkon.talkon.properties.ServerProperties;
import com.talkon.talkon.repositories.user.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties({
        OpenApiProperties.class,
        ServerProperties.class
})

public class TalkOnApplication {

    @Value("${spring.sql.init.mode}")
    String init;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(TalkOnApplication.class, args);
    }


    @Bean
    CommandLineRunner runner() {
        return (args -> {
            if (init.equals("always")) {
            List<String> ids = Arrays.asList(
                    "c45ab69a-057f-43ae-95b4-e8e7300c4e84",
                    "0a7c8fe5-1291-4043-855b-c937b9371889",
                    "f1a897aa-7ce0-4695-a3d6-977eaed981f0",
                    "66f4acdb-3e8c-4a83-8000-80dc1bb644db",
                    "6d47e929-fcca-4607-9601-9d5d2ff98a83",
                    "92a73cbb-7b5f-43a7-b45b-41ceb2aaad1e");
                List<User> users = repository.findAllById(ids);
                users.forEach(user -> user.setPassword(passwordEncoder.encode(user.getPassword())));
                repository.saveAll(users);
            }
        });

    }
}
