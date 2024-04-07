package org.codeforpizza.productionservice;

import org.codeforpizza.productionservice.modell.*;
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
                        , ActRepository actRepository, CastRepository castRepository
                        , ManifestRepository manifestRepository,GarmentRepository garmentRepository
    ,PerformerRepository performerRepository) {
        return args -> {
            if (roleRepository.findByAuthority("ADMIN").isPresent()) return;
            Role adminRole = roleRepository.save(new Role("ADMIN"));

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);

            Garment adminGarment = new Garment("Black jacket", "its black");
            garmentRepository.save(adminGarment);

            Costume adminCostume = new Costume("admin costume");
            adminCostume.getGarments().add(adminGarment);

            Act adminAct = new Act("Act admin");
            adminAct.getCostumes().add(adminCostume);
            actRepository.save(adminAct);

            Performer adminPerformer = new Performer("admin", "Doe");
            adminPerformer.getActs().add(adminAct);
            performerRepository.save(adminPerformer);

            Cast adminCast = new Cast("admin cast");
            adminCast.getPerformers().add(adminPerformer);
            castRepository.save(adminCast);

            Manifest adminManifest = new Manifest("Manifest admin", 2026);
            adminManifest.getCasts().add(adminCast);

            Production adminProduction = new Production(2026L, "Production admin",true, "Description admin");
            adminProduction.getManifests().add(adminManifest);
            productionRepository.save(adminProduction);

            List<Production> adminProductions = new ArrayList<>();
            adminProductions.add(adminProduction);

            ApplicationUser admin = new ApplicationUser("admin", passwordEncode.encode("password2"), adminRoles, adminProductions);

            userRepository.save(admin);



            Role userRole = roleRepository.save(new Role("USER"));
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(userRole);
            Garment garment = new Garment("Black jacket", "its black");
            garmentRepository.save(garment);

            Costume costume = new Costume("Black costume");
            costume.getGarments().add(garment);

            Act act = new Act("Act 1");
            act.getCostumes().add(costume);
            actRepository.save(act);

            Performer performer = new Performer("John", "Doe");
            performer.getActs().add(act);
            performerRepository.save(performer);

            Cast cast = new Cast("Main cast");
            cast.getPerformers().add(performer);
            castRepository.save(cast);

            Manifest manifest = new Manifest("Manifest 1", 2021);
            manifest.getCasts().add(cast);

            Production production = new Production(2005L, "Production 1",true, "Description 1");
            production.getManifests().add(manifest);
            productionRepository.save(production);

            List<Production> productions = new ArrayList<>();
            productions.add(production);

            ApplicationUser user = new ApplicationUser( "user", passwordEncode.encode("password1"), userRoles, productions);
            userRepository.save(user);


        };
    }

}
