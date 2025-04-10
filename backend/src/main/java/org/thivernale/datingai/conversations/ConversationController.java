package org.thivernale.datingai.conversations;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/conversations")
public class ConversationController {
    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @PostMapping
    public Conversation createNewConversation(@RequestBody @Valid final ConversationRequest conversationRequest) {
        return conversationService.createConversation(conversationRequest);
    }

    @PostMapping("/{conversationId}")
    public Conversation addMessageToConversation(
        @PathVariable String conversationId,
        @RequestBody @Valid @NotNull final Message message
    ) {
        return conversationService.addMessageToConversation(conversationId, message);
    }
}
