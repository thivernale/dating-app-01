package org.thivernale.datingai.conversations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {
    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @PostMapping
    public Conversation createNewConversation(@RequestBody @Valid final ConversationRequest conversationRequest) {
        return conversationService.findOrCreateConversation(conversationRequest);
    }

    @PostMapping("/{conversationId}")
    public Conversation addMessageToConversation(
        @PathVariable String conversationId,
        @RequestBody @Valid @NotNull final Message message
    ) {
        return conversationService.addMessageToConversation(conversationId, message);
    }

    @GetMapping("/{conversationId}")
    public Conversation getConversation(@PathVariable String conversationId) {
        return conversationService.getConversation(conversationId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation not found"));
    }
}
