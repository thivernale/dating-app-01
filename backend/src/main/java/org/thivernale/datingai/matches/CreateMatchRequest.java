package org.thivernale.datingai.matches;

import jakarta.validation.constraints.NotBlank;

public record CreateMatchRequest(
    @NotBlank
    String profileId
) {
}
