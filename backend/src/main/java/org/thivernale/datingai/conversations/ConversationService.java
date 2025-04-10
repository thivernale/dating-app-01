package org.thivernale.datingai.conversations;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.thivernale.datingai.profiles.ProfileRepository;

import java.util.Collections;
import java.util.UUID;

@Service
class ConversationService {
    private final ConversationRepository repository;
    private final ProfileRepository profileRepository;

    ConversationService(ConversationRepository repository, ProfileRepository profileRepository) {
        this.repository = repository;
        this.profileRepository = profileRepository;
    }

    public Conversation createConversation(ConversationRequest conversationRequest) {
        profileRepository.findById(conversationRequest.profileId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));

        Conversation conversation = new Conversation(
            UUID.randomUUID()
                .toString(),
            conversationRequest.profileId(),
            Collections.emptyList());
        return repository.save(conversation);
    }
}
