package org.thivernale.datingai.conversations;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record Message(@NotBlank String authorId, @NotBlank String text, LocalDateTime sentAt) {
}
