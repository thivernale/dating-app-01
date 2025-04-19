package org.thivernale.datingai;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thivernale.datingai.profiles.ProfileCreationService;

@SpringBootApplication
public class BackendApplication {

    private final ProfileCreationService profileCreationService;

    public BackendApplication(ProfileCreationService profileCreationService) {
        this.profileCreationService = profileCreationService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    @ConditionalOnProperty("dating-ai.init-profiles.enabled")
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            profileCreationService.saveProfilesToDatabase();
        };
    }

}
