package org.scoreboard.match.start;

import lombok.RequiredArgsConstructor;
import org.scoreboard.command.CommandHandler;
import org.scoreboard.match.model.Match;
import org.scoreboard.match.repository.MatchRepository;

@RequiredArgsConstructor
public class StartMatchCommandHandler implements CommandHandler<StarMatchCommand, Match> {
    private final MatchRepository matchRepository;
    @Override
    public Match handle(StarMatchCommand command) {
        return null;
    }
}
