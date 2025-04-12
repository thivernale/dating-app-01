package org.thivernale.datingai.matches;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.thivernale.datingai.conversations.Conversation;
import org.thivernale.datingai.conversations.ConversationRepository;
import org.thivernale.datingai.profiles.Profile;
import org.thivernale.datingai.profiles.ProfileRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final ConversationRepository conversationRepository;
    private final ProfileRepository profileRepository;

    public MatchService(
        MatchRepository matchRepository,
        ConversationRepository conversationRepository,
        ProfileRepository profileRepository
    ) {
        this.matchRepository = matchRepository;
        this.conversationRepository = conversationRepository;
        this.profileRepository = profileRepository;
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Match createOrGetMatch(CreateMatchRequest createMatchRequest) {
        System.out.println("createOrGetMatch for profileId " + createMatchRequest.profileId());
        return matchRepository.findMatchByProfile_Id(createMatchRequest.profileId())
            .orElseGet(() ->
                matchRepository.save(new Match(
                    UUID.randomUUID()
                        .toString(),
                    getProfile(createMatchRequest.profileId()),
                    createOrGetConversation(createMatchRequest.profileId()).id()
                ))
            );
    }

    private Conversation createOrGetConversation(String profileId) {
        return conversationRepository.findOneByProfileId(profileId)
            .orElseGet(() -> conversationRepository.save(new Conversation(
                UUID.randomUUID()
                    .toString(),
                profileId,
                Collections.emptyList())));
    }

    private Profile getProfile(String profileId) {
        return profileRepository.findById(profileId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found"));
    }

}
