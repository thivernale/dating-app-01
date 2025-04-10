package org.thivernale.datingai;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.thivernale.datingai.conversations.Conversation;
import org.thivernale.datingai.conversations.ConversationRepository;
import org.thivernale.datingai.conversations.Message;
import org.thivernale.datingai.profiles.Gender;
import org.thivernale.datingai.profiles.Profile;
import org.thivernale.datingai.profiles.ProfileRepository;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class BackendApplication {

    private final ProfileRepository profileRepository;
    private final ConversationRepository conversationRepository;

    public BackendApplication(ProfileRepository profileRepository, ConversationRepository conversationRepository) {
        this.profileRepository = profileRepository;
        this.conversationRepository = conversationRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            // reset
            conversationRepository.deleteAll();

            // save
            profileRepository.save(new Profile(
                "001", "Some", "Name", 23, "I am Some Name", "", "Caucasian", Gender.UNKNOWN, "?"
            ));
            profileRepository.save(new Profile(
                "002", "Jane", "Doe", 42, "Bio unknown", "", "Caucasian", Gender.FEMALE, "?"
            ));
            conversationRepository.save(new Conversation(
                "CONV001",
                "001",
                List.of(new Message("-1", "Hello! How are you doing?", LocalDateTime.now()))));

            // find
            profileRepository.findAll()
                .forEach(System.out::println);
            conversationRepository.findAll()
                .forEach(System.out::println);
        };
    }

}
