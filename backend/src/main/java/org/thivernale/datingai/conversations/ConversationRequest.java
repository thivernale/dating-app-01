package org.thivernale.datingai.conversations;

import jakarta.validation.constraints.NotBlank;

public record ConversationRequest(
    @NotBlank
    String profileId
) {
}
