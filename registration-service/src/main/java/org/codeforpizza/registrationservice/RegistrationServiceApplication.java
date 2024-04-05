package org.codeforpizza.registrationservice;

import org.codeforpizza.registrationservice.models.ApplicationUser;
import org.codeforpizza.registrationservice.models.Measurements;
import org.codeforpizza.registrationservice.models.Performer;
import org.codeforpizza.registrationservice.models.Role;
import org.codeforpizza.registrationservice.repository.MeasurementsRepository;
import org.codeforpizza.registrationservice.repository.PerformerRepository;
import org.codeforpizza.registrationservice.repository.RoleRepository;
import org.codeforpizza.registrationservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class RegistrationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistrationServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner run(MeasurementsRepository measurementsRepository, PerformerRepository performerRepository, RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode) {
        return args -> {
            if (roleRepository.findByAuthority("ADMIN").isPresent()) return;
            Role adminRole = roleRepository.save(new Role("ADMIN"));

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);
            ApplicationUser admin = new ApplicationUser("admin", passwordEncode.encode("password2"), adminRoles);
            Measurements adminMeasurements = new Measurements(0, 0, 0, 0, 0);
            measurementsRepository.save(adminMeasurements);
            Performer adminPerformer = new Performer("Admin", "", "", "", "", adminMeasurements);
            performerRepository.save(adminPerformer);
            userRepository.save(admin);

            Role userRole = roleRepository.save(new Role("USER"));
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);
            Measurements measurements = new Measurements(179, 43, 50, 50, 44);
            measurementsRepository.save(measurements);
            Performer performer = new Performer("John", "Doe", "john@gmail.com", "123456789", "Dancer", measurements);
            performerRepository.save(performer);
            ApplicationUser user = new ApplicationUser(2, "user", passwordEncode.encode("password1"), userRoles, performer);
            userRepository.save(user);


        };
    }
}
