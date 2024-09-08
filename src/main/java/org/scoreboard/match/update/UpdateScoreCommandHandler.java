package org.scoreboard.match.update;

import lombok.RequiredArgsConstructor;
import org.scoreboard.command.CommandHandler;
import org.scoreboard.match.model.Match;
import org.scoreboard.match.repository.MatchRepository;

@RequiredArgsConstructor
public class UpdateScoreCommandHandler implements CommandHandler<UpdateScoreCommand, Match> {
    private final MatchRepository matchRepository;

    @Override
    public Match handle(UpdateScoreCommand command) {
        //todo 1. find match and update, or else create a new one with the score.
        return null;
    }
}
