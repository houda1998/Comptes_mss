package org.sid.authenticationservice;

import org.sid.authenticationservice.entities.AppRole;
import org.sid.authenticationservice.entities.AppUser;
import org.sid.authenticationservice.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class AuthenticationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServiceApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner start(AccountService accountService) {
        return args -> {
            accountService.addNewRole(new AppRole(null, "USER"));
            accountService.addNewRole(new AppRole(null, "ADMIN"));
            accountService.addNewRole(new AppRole(null, "COMPTE_MANAGER"));
            accountService.addNewRole(new AppRole(null, "CLIENT_MANAGER"));

            accountService.addNewUser(new AppUser(null, "houda", "houda98", new ArrayList<>()));
            accountService.addNewUser(new AppUser(null, "admin", "admin", new ArrayList<>()));
            accountService.addNewUser(new AppUser(null, "user1", "1234", new ArrayList<>()));
            accountService.addNewUser(new AppUser(null, "user2", "1234", new ArrayList<>()));

            accountService.addRoleToUser("houda", "USER");
            accountService.addRoleToUser("admin", "USER");
            accountService.addRoleToUser("admin", "ADMIN");
            accountService.addRoleToUser("user1", "USER");
            accountService.addRoleToUser("user1", "COMPTE_MANAGER");
            accountService.addRoleToUser("user2", "USER");
            accountService.addRoleToUser("user2", "CLIENT_MANAGER");
        };
    }

}
