package org.thivernale.datingai.conversations;

import org.springframework.ai.chat.messages.AbstractMessage;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.thivernale.datingai.profiles.Profile;
import org.thivernale.datingai.profiles.ProfileRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;
    private final ProfileRepository profileRepository;
    private final ChatModel chatModel;

    @Value("#{${dating-ai.character.user:\"{id:'-1'}\"}.id}")
    private String characterId;

    ConversationService(
        ConversationRepository conversationRepository,
        ProfileRepository profileRepository,
        @Qualifier("openAiChatModel") ChatModel chatModel
    ) {
        this.conversationRepository = conversationRepository;
        this.profileRepository = profileRepository;
        this.chatModel = chatModel;
    }

    Conversation findOrCreateConversation(ConversationRequest conversationRequest) {
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

    Optional<Conversation> getConversation(String conversationId) {
        return conversationRepository.findById(conversationId);
    }

    Conversation addMessageToConversation(String conversationId, Message message) {
        Conversation conversation = getConversation(conversationId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation not found"));

        if (!characterId
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

        if (characterId
            .equals(message.authorId())) {
            generateProfileResponse(conversation);
        }

        return conversationRepository.save(conversation);
    }

    void generateProfileResponse(Conversation conversation) {
        Profile otherProfile = getProfile(conversation.profileId());
        Profile ownProfile = getProfile(characterId);

        List<AbstractMessage> list = new ArrayList<>();
        list.add(new SystemMessage("""
            Pretend to be a dating app.
            Take the role of this profile: Name: %s %s, Age: %d, Gender: %s, Ethnicity: %s, Bio: %s, Myers Briggs personality type: %s.
            You are chatting with this profile: Name: %s %s, Age: %d, Gender: %s, Ethnicity: %s, Bio: %s.
            """.formatted(
            otherProfile.firstName(),
            otherProfile.lastName(),
            otherProfile.age(),
            otherProfile.gender(),
            otherProfile.ethnicity(),
            otherProfile.bio(),
            otherProfile.myersBriggsPersonalityType(),
            ownProfile.firstName(),
            ownProfile.lastName(),
            ownProfile.age(),
            ownProfile.gender(),
            ownProfile.ethnicity(),
            ownProfile.bio()
        )));

        list.addAll(conversation.messages()
            .stream()
            .map(message -> characterId.equals(message.authorId()) ?
                new UserMessage(message.text()) :
                new AssistantMessage(message.text())
            )
            .toList());
        String responseMessage =
            chatModel.call(new Prompt(new ArrayList<org.springframework.ai.chat.messages.Message>(list)))
                .getResult()
                .getOutput()
                .getText()
                .trim();

        conversation.messages()
            .add(new Message(otherProfile.id(), responseMessage, LocalDateTime.now()));
    }
}
