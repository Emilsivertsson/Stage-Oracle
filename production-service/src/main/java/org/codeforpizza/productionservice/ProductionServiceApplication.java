package org.codeforpizza.productionservice;


import org.codeforpizza.productionservice.modell.entitys.*;
import org.codeforpizza.productionservice.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ProductionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductionServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository
                        , PasswordEncoder passwordEncode, ProductionRepository productionRepository
                        , ActRepository actRepository, CastRepository castRepository, MeasurementsRepository measurementsRepository
                        , ManifestRepository manifestRepository,CostumeRepository costumeRepository,GarmentRepository garmentRepository
    ,PerformerRepository performerRepository) {

        return args -> {

            if (roleRepository.findByAuthority("ADMIN").isPresent()) return;
            Role adminRole = roleRepository.save(new Role("ADMIN"));

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);

            Garment adminGarment = new Garment("Black jacket", "its black");
            Costume adminCostume = new Costume("admin costume");
            Act adminAct = new Act("Act admin");
            Measurements adminMeasurements = new Measurements( 1.0, 1.0, 1.0, 1.0, 1.0);
            Performer adminPerformer = new Performer("admin", "Doe", "admin@admin.com", "123456789", "admin");
            Cast adminCast = new Cast("admin cast");
            Manifest adminManifest = new Manifest("Manifest admin", 2026);
            Production adminProduction = new Production(2026L, "Production admin",true, "Description admin");
            List<Production> adminProductions = new ArrayList<>();
            ApplicationUser admin = new ApplicationUser("admin", passwordEncode.encode("password2"), adminRoles, adminProductions);

            userRepository.save(admin);
            adminProduction.setApplicationUser(admin);
            productionRepository.save(adminProduction);
            adminManifest.setProduction(adminProduction);
            manifestRepository.save(adminManifest);
            adminCast.setManifest(adminManifest);
            castRepository.save(adminCast);
            adminPerformer.setCast(adminCast);
            adminPerformer.setMeasurements(adminMeasurements);
            measurementsRepository.save(adminMeasurements);
            performerRepository.save(adminPerformer);
            adminAct.setPerformer(adminPerformer);
            actRepository.save(adminAct);
            adminCostume.setAct(adminAct);
            costumeRepository.save(adminCostume);
            adminGarment.setCostume(adminCostume);
            garmentRepository.save(adminGarment);


            Role userRole = roleRepository.save(new Role("USER"));
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);
            Garment garment = new Garment("Black jacket", "its black");
            Costume costume = new Costume("Black costume");
            Act act = new Act("Act 1");

            Measurements measurements = new Measurements( 189, 45, 50, 52, 62);
            Performer performer = new Performer("Jane", "Doe","Jane@Doe.com", "123456789", "Choir");
            Cast cast = new Cast("Choir");
            Manifest manifest = new Manifest("Manifest 1", 2021);
            Production production = new Production(2005L, "Hamlet",true, "Producer: William Shakespeare, director: John Doe");
            List<Production> productions = new ArrayList<>();
            ApplicationUser user = new ApplicationUser( "user", passwordEncode.encode("password1"), userRoles, productions);

            userRepository.save(user);
            production.setApplicationUser(user);
            productionRepository.save(production);
            manifest.setProduction(production);
            manifestRepository.save(manifest);
            cast.setManifest(manifest);
            castRepository.save(cast);
            performer.setCast(cast);
            performer.setMeasurements(measurements);
            measurementsRepository.save(measurements);
            performerRepository.save(performer);
            act.setPerformer(performer);
            actRepository.save(act);
            costume.setAct(act);
            costumeRepository.save(costume);
            garment.setCostume(costume);
            garmentRepository.save(garment);











        };
    }

}
