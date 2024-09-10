package org.scoreboard.match.update;

import lombok.RequiredArgsConstructor;
import org.scoreboard.command.CommandHandler;
import org.scoreboard.match.model.Match;
import org.scoreboard.match.repository.MatchRepository;
import org.scoreboard.match.repository.exception.MatchNotFoundException;

@RequiredArgsConstructor
public class UpdateScoreCommandHandler implements CommandHandler<UpdateScoreCommand, Match> {
    private final MatchRepository matchRepository;

    @Override
    public Match handle(UpdateScoreCommand command) {
        var updatedMatch = getMatch(command)
                .updateScore(command.getMatchScore());

        matchRepository.save(updatedMatch);
        return updatedMatch;
    }

    private Match getMatch(UpdateScoreCommand command) {
        return matchRepository.findById(command.getMatchId())
                .orElseThrow(() -> new MatchNotFoundException(command.getMatchId()));
    }
}
