package org.thivernale.datingai;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thivernale.datingai.profiles.ProfileCreationService;

@SpringBootApplication
public class BackendApplication {

    private final ProfileCreationService profileCreationService;
    @Autowired
    @Qualifier("openAiChatModel")
    private ChatModel chatModel;

    public BackendApplication(ProfileCreationService profileCreationService) {
        this.profileCreationService = profileCreationService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    //@Bean
    public CommandLineRunner commandLineRunnerOpenai(ApplicationContext ctx) {
        return args -> {
            System.out.println(chatModel.call(new Prompt(
                    "Hello! Are you ChatGPT?"
                ))
                .getResult()
                .getOutput()
                .getText()
                .trim());
        };
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            profileCreationService.saveProfilesToDatabase();
        };
    }

}
