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
            ,PerformerRepository performerRepository, AwanRespository awanRespository) {

        return args -> {


            if (roleRepository.findByAuthority("ADMIN").isPresent()) return;
            Role adminRole = roleRepository.save(new Role("ADMIN"));

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);

            Garment adminGarment = new Garment("Black jacket", "its black", false);
            Costume adminCostume = new Costume("admin costume");
            Act adminAct = new Act("Act admin");
            Measurements adminMeasurements = new Measurements( 1.0, 1.0, 1.0, 1.0, 1.0);
            Performer adminPerformer = new Performer("admin", "Doe", "admin@admin.com", "123456789", "admin");
            Cast adminCast = new Cast("admin cast");
            Manifest adminManifest = new Manifest("Manifest admin", 2026);
            Production adminProduction = new Production(2026L, "Production admin",true, "Description admin");
            List<Production> adminProductions = new ArrayList<>();
            AwanChats adminChat = new AwanChats( "admin", "admin");
            List<AwanChats> adminChats = new ArrayList<>();
            ApplicationUser admin = new ApplicationUser("admin", passwordEncode.encode("password2"), adminRoles, adminProductions, adminChats);

            userRepository.save(admin);
            adminProduction.setApplicationUser(admin);
            productionRepository.save(adminProduction);
            adminManifest.setProduction(adminProduction);
            adminChat.setApplicationUser(admin);
            awanRespository.save(adminChat);
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
            Garment garment = new Garment("Soldier jacket", "Grimy, black and red with gold buttons", true);
            Garment garment2 = new Garment("Soldier trousers", "Holes on knees, black with red trimmings", false);
            Garment garment3 = new Garment("Soldier cap", "Black, worn out, with a brim and emblem", false);
            Garment garment4 = new Garment( "King robe", "Red, gold trimmings, with a crown", true);
            Garment garment5 = new Garment("King crown", "Gold, with red rubies", false);

            Costume costume = new Costume("Soldier costume");
            Costume costume2 = new Costume("King costume");
            Act act = new Act("Act 1");
            Act act2 = new Act("Act 2");

            Measurements measurements = new Measurements( 189, 45, 50, 52, 62);
            Performer performer = new Performer("Jane", "Doe","Jane@Doe.com", "123456789", "Choir");
            Cast cast = new Cast("Mens Choir");
            Cast cast2 = new Cast("Womens Choir");
            Cast cast3 = new Cast("Dancers");
            Manifest manifest = new Manifest("Original version, summer of 2021", 2021);
            Manifest manifest2 = new Manifest("Small Version, summer of 2025", 2025);
            Production production = new Production(2005L, "Hamlet",true, "Producer: William Shakespeare, director: John Doe");
            Production production2 = new Production(2006L, "Macbeth",true, "Producer: William Shakespeare, director: John Doe");
            Production production3 = new Production(2007L, "Othello",true, "Producer: William Shakespeare, director: John Doe");
            Production production4 = new Production(2008L, "Romeo and Juliet",true, "Producer: William Shakespeare, director: John Doe");
            List<Production> productions = new ArrayList<>();
            AwanChats chat = new AwanChats( "What does soldiers wear", "What a great question!\\n\\nThe type of clothing and gear that soldiers wear can vary depending on their branch, rank, and specific mission or environment. However, here are some general items that many soldiers typically wear:\\n\\n**Uniform:**\\n\\n* Army Combat Uniform (ACU) - a camouflage-patterned uniform used for everyday wear in combat zones.\\n* Army Service Uniform (ASU) - a more formal uniform worn for official events, ceremonies, and daily duties.\\n\\n**Outerwear:**\\n\\n* Camouflage poncho or rain jacket\\n* Parka or insulated coat\\n* Windbreaker or lightweight jacket\\n\\n**Headgear:**\\n\\n* Kevlar helmet (e.g., Advanced Combat Helmet or Modular Integrated Communication Helmet)\\n* Beret or patrol cap (depending on the branch and rank)\\n\\n**Footwear:**\\n\\n* Combat boots (e.g., Blackhawk or Timberland-style boots)\\n* Jungle boots or tactical shoes (for jungle or desert environments)\\n* Snow boots (for winter operations)\\n\\n**Body Armor:**\\n\\n* Interceptor Body Armor (IBA) vest or plate carrier\\n* Modular Tactical Vest (MTV) with ceramic plates\\n\\n**Other Gear:**\\n\\n* Web belt or nylon belt\\n* Holster for pistol or rifle\\n* Magazine pouches and ammunition cans\\n* First aid kit or medical pouch\\n* Water bottle or hydration pack\\n* Sleeping bag or bivvy sack (for field operations)\\n\\n**Specialized Gear:**\\n\\n* Night vision goggles or binoculars\\n* Thermal imaging devices (e.g., thermal scopes or cameras)\\n* Hearing protection (e.g., earplugs or earmuffs)\\n* Gas mask or chemical warfare agent protective equipment (CWPE)\\n* Environmental protective gear (e.g., cold-weather gloves or hot-weather headgear)\\n\\nPlease note that this is not an exhaustive list, and actual gear may vary depending on the soldiers unit, mission, and environment. Additionally, some gear may be specific to certain branches or countries militaries.");
            List<AwanChats> chats = new ArrayList<>();
            ApplicationUser user = new ApplicationUser( "user", passwordEncode.encode("password1"), userRoles, productions, chats);

            userRepository.save(user);
            production.setApplicationUser(user);
            production2.setApplicationUser(user);
            production3.setApplicationUser(user);
            production4.setApplicationUser(user);
            productionRepository.save(production);
            productionRepository.save(production2);
            productionRepository.save(production3);
            productionRepository.save(production4);
            manifest.setProduction(production);
            manifest2.setProduction(production);
            chat.setApplicationUser(user);
            awanRespository.save(chat);
            manifestRepository.save(manifest);
            manifestRepository.save(manifest2);
            cast.setManifest(manifest);
            cast2.setManifest(manifest);
            cast3.setManifest(manifest);
            castRepository.save(cast);
            castRepository.save(cast2);
            castRepository.save(cast3);
            performer.setCast(cast);
            performer.setMeasurements(measurements);
            measurementsRepository.save(measurements);
            performerRepository.save(performer);
            act.setPerformer(performer);
            act2.setPerformer(performer);
            actRepository.save(act);
            actRepository.save(act2);
            costume.setAct(act);
            costume2.setAct(act2);
            costumeRepository.save(costume);
            costumeRepository.save(costume2);
            garment.setCostume(costume);
            garment2.setCostume(costume);
            garment3.setCostume(costume);
            garment4.setCostume(costume2);
            garment5.setCostume(costume2);
            garmentRepository.save(garment);
            garmentRepository.save(garment2);
            garmentRepository.save(garment3);
            garmentRepository.save(garment4);
            garmentRepository.save(garment5);

        };
    }

}
