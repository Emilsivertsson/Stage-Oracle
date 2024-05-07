package org.codeforpizza.registrationservice;

import org.codeforpizza.registrationservice.models.entitys.ApplicationUser;
import org.codeforpizza.registrationservice.models.entitys.Measurements;
import org.codeforpizza.registrationservice.models.entitys.Performer;
import org.codeforpizza.registrationservice.models.entitys.Role;
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
            if (!measurementsRepository.existsById(adminMeasurements.getId())) {
                measurementsRepository.save(adminMeasurements);
            }
            Performer adminPerformer = new Performer("Admin", "", "", "", "", adminMeasurements);
            if (!performerRepository.existsById(1l)) {
                performerRepository.save(adminPerformer);
            }
            if (!userRepository.existsById(1l)) {
                userRepository.save(admin);
            }

            Role userRole = roleRepository.save(new Role("USER"));
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);
            Measurements johnMeasurements = new Measurements(179, 43, 50, 50, 44);
            if (!measurementsRepository.existsById(johnMeasurements.getId())) {
                measurementsRepository.save(johnMeasurements);
            }
            Performer johnPerformer = new Performer("John", "Doe", "john@gmail.com", "123456789", "Singer", johnMeasurements);
            if (!performerRepository.existsById(2l)) {
                performerRepository.save(johnPerformer);
            }
            ApplicationUser johnUser = new ApplicationUser(2L, "user", passwordEncode.encode("password1"), userRoles, johnPerformer);
            if (!userRepository.existsById(2l)) {
                userRepository.save(johnUser);
            }

            Measurements janeMeasurements = new Measurements(160, 40, 45, 45, 40);
            if (!measurementsRepository.existsById(janeMeasurements.getId())) {
                measurementsRepository.save(janeMeasurements);
            }
            Performer janePerformer = new Performer("Jane", "Doe", "jane@gmail.com" , "123456789", "Dancer", janeMeasurements);
            if (!performerRepository.existsById(3l)) {
                performerRepository.save(janePerformer);
            }
            ApplicationUser janeUser = new ApplicationUser(3L, "jane", passwordEncode.encode("password1"), userRoles, janePerformer);
            if (!userRepository.existsById(3l)) {
                userRepository.save(janeUser);
            }

            Measurements jackMeasurements = new Measurements(180, 45, 50, 50, 45);
            if (!measurementsRepository.existsById(jackMeasurements.getId())) {
                measurementsRepository.save(jackMeasurements);
            }
            Performer jackPerformer = new Performer("Jack", "Doe", "jack@msn.com", "123456789", "Singer", jackMeasurements);
            if (!performerRepository.existsById(4l)) {
                performerRepository.save(jackPerformer);
            }
            ApplicationUser jackUser = new ApplicationUser(4L, "jack", passwordEncode.encode("password1"), userRoles, jackPerformer);
            if (!userRepository.existsById(4l)) {
                userRepository.save(jackUser);
            }

            Measurements jillMeasurements = new Measurements(160, 40, 45, 45, 40);
            if (!measurementsRepository.existsById(jillMeasurements.getId())) {
                measurementsRepository.save(jillMeasurements);
            }
            Performer jillPerformer = new Performer("Jill", "Doe", "jill@work.com", "123456789", "Dancer", jillMeasurements);
            if (!performerRepository.existsById(5l)) {
                performerRepository.save(jillPerformer);
            }
            ApplicationUser jillUser = new ApplicationUser (5L, "jill", passwordEncode.encode("password1"), userRoles, jillPerformer);
            if (!userRepository.existsById(5l)) {
                userRepository.save(jillUser);
            }




        };


    }

}
