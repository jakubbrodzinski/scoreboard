package org.scoreboard.domain.match.update;

import lombok.RequiredArgsConstructor;
import org.scoreboard.domain.command.CommandHandler;
import org.scoreboard.domain.match.model.Match;
import org.scoreboard.domain.match.repository.MatchRepository;
import org.scoreboard.domain.match.repository.exception.MatchNotFoundException;

@RequiredArgsConstructor
public class UpdateScoreCommandHandler implements CommandHandler<UpdateScoreCommand, Match> {
    private final MatchRepository matchRepository;

    @Override
    public Match handle(UpdateScoreCommand command) {
        var updatedMatch = getMatch(command)
                .updateScore(command.getMatchScore());

        matchRepository.update(updatedMatch);
        return updatedMatch;
    }

    private Match getMatch(UpdateScoreCommand command) {
        return matchRepository.findById(command.getMatchId())
                .orElseThrow(() -> new MatchNotFoundException(command.getMatchId()));
    }
}
