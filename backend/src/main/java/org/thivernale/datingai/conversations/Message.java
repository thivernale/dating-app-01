package org.thivernale.datingai.conversations;

import java.time.LocalDateTime;

public record Message(String authorId, String text, LocalDateTime sentAt) {
}
