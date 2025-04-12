package org.thivernale.datingai.matches;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MatchRepository extends MongoRepository<Match, String> {
    Optional<Match> findMatchByProfile_Id(String profileId);
}
