package org.thivernale.datingai.profiles;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ProfileCreationService {
    private static final String PROFILES_FILE_PATH = "src/main/resources/static/profiles.json";
    private final ProfileRepository profileRepository;
    @Value("#{${dating-ai.character.user}}")
    private Map<String, String> userProfileProperties;

    public ProfileCreationService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void saveProfilesToDatabase() {
        // 1. save profiles from file
        try (FileReader fileReader = new FileReader(PROFILES_FILE_PATH)) {
            var profiles = new ObjectMapper().readValue(fileReader, new TypeReference<List<Profile>>() {
            });
            profileRepository.deleteAll();
            profileRepository.saveAll(profiles);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 2. save own profile
        profileRepository.save(new Profile(
            userProfileProperties.get("id"),
            userProfileProperties.get("firstName"),
            userProfileProperties.get("lastName"),
            Integer.parseInt(userProfileProperties.get("age")),
            userProfileProperties.get("bio"),
            userProfileProperties.get("imageUrl"),
            userProfileProperties.get("ethnicity"),
            Gender.valueOf(userProfileProperties.get("gender")),
            userProfileProperties.get("myersBriggsPersonalityType")
        ));
    }
}
