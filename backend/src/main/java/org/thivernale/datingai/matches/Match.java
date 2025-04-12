package org.thivernale.datingai.matches;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.thivernale.datingai.profiles.Profile;

@Document
public record Match(
    @Id
    String id,
    Profile profile,
    String conversationId
) {
}
