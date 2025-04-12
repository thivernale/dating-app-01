package org.thivernale.datingai.profiles;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfileRepository extends MongoRepository<Profile, String> {
    @Aggregation(
        pipeline = {
            "{ $match: { _id : { $ne: '?0' } } }",
            "{ $sample: { size: 1 } }"
        }
    )
    Profile getRandomProfile(String excludedId);
}
