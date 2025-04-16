package org.thivernale.datingai.matches;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService profileService) {
        this.matchService = profileService;
    }

    @GetMapping
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }

    @PostMapping
    public Match createMatch(@RequestBody @Valid final CreateMatchRequest createMatchRequest) {
        return matchService.createOrGetMatch(createMatchRequest);
    }
}
