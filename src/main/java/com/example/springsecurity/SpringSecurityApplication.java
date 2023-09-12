package com.example.springsecurity;

import com.example.springsecurity.controller.Role;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication(scanBasePackages = "com.example.springsecurity")
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Autowired
    private UserRepository userRepo;

    @Bean
    CommandLineRunner myCommandLineRunnerBean() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                User newUser = new User("Boris", "azerty");
                User newUser1 = new User("Igor", "qwerty");

                newUser.setRoles(Arrays.asList(new Role("User")));
                newUser1.setRoles(Arrays.asList(new Role("User")));

                userRepo.save(newUser);
                userRepo.save(newUser1);
            }
        };
    }
}
