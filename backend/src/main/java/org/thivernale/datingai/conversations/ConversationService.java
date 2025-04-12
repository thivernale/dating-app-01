package org.thivernale.datingai.conversations;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.thivernale.datingai.profiles.Profile;
import org.thivernale.datingai.profiles.ProfileRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;
    private final ProfileRepository profileRepository;

    ConversationService(
        ConversationRepository conversationRepository,
        ProfileRepository profileRepository
    ) {
        this.conversationRepository = conversationRepository;
        this.profileRepository = profileRepository;
    }

    public Conversation findOrCreateConversation(ConversationRequest conversationRequest) {
        getProfile(conversationRequest.profileId());

        return conversationRepository.findOneByProfileId(conversationRequest.profileId())
            .orElseGet(() -> {
                Conversation conversation = new Conversation(
                    UUID.randomUUID()
                        .toString(),
                    conversationRequest.profileId(),
                    Collections.emptyList());
                return conversationRepository.save(conversation);
            });
    }

    private Profile getProfile(String profileId) {
        return profileRepository.findById(profileId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));
    }

    public Optional<Conversation> getConversation(String conversationId) {
        return conversationRepository.findById(conversationId);
    }

    public Conversation addMessageToConversation(String conversationId, Message message) {
        Conversation conversation = getConversation(conversationId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation not found"));

        final String OUR_PROFILE_ID = "-1"; //TODO define later

        if (!OUR_PROFILE_ID
            .equals(message.authorId())) {
            if (!conversation.profileId()
                .equals(message.authorId())) {
                throw new ResponseStatusException(
                    HttpStatus.EXPECTATION_FAILED,
                    "Conversation and message profiles don't match");
            }
            getProfile(message.authorId());
        }

        conversation.messages()
            .add(new Message(message.authorId(), message.text(), LocalDateTime.now()));
        return conversationRepository.save(conversation);
    }
}
