package org.thivernale.datingai.profiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    @Value("#{${dating-ai.character.user}.id}:'-1'")
    private String characterId;

    ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    Profile getRandomProfile() {
        return profileRepository.getRandomProfile(characterId);
    }
}
