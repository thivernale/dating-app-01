package org.thivernale.datingai.profiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    @Value("#{${dating-ai.character.user}.id}")
    private String characterId;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile getRandomProfile() {
        return profileRepository.getRandomProfile(characterId);
    }
}
