package org.thivernale.datingai.conversations;

import java.util.List;

public record Conversation(
    String id,
    String profileId,
    List<Message> messages
) {
}
