package com.example.springsecurity;

import com.example.springsecurity.entity.Role;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = "com.example.springsecurity")
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner myCommandLineRunnerBean() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                User newUser = new User("Boris", passwordEncoder.encode("azerty")); // mdp encodé
                User newUser1 = new User("Igor", "qwerty"); // en dur

                newUser.setRoles(Arrays.asList(new Role("User")));
                newUser1.setRoles(Arrays.asList(new Role("User")));

                userRepo.save(newUser);
                userRepo.save(newUser1);
            }
        };
    }
}
