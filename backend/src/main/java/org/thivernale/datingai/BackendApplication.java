package org.thivernale.datingai;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thivernale.datingai.profiles.Gender;
import org.thivernale.datingai.profiles.Profile;
import org.thivernale.datingai.profiles.ProfileRepository;

@SpringBootApplication
public class BackendApplication {

    private final ProfileRepository profileRepository;

    public BackendApplication(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            profileRepository.save(new Profile(
                "001", "Some", "Name", 23, "I am Some Name", "", "Caucasian", Gender.UNKNOWN, "?"
            ));
            profileRepository.findAll()
                .forEach(System.out::println);
        };
    }

}
