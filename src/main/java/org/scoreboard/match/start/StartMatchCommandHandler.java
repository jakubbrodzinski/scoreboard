package org.scoreboard.match.start;

import lombok.RequiredArgsConstructor;
import org.scoreboard.command.CommandHandler;
import org.scoreboard.match.factory.MatchFactory;
import org.scoreboard.match.model.Match;
import org.scoreboard.match.repository.MatchRepository;

@RequiredArgsConstructor
public class StartMatchCommandHandler implements CommandHandler<StarMatchCommand, Match> {
    private final MatchFactory matchFactory;
    private final MatchRepository matchRepository;

    @Override
    public Match handle(StarMatchCommand command) {
        var match = matchFactory.create(command.getHomeTeam(), command.getAwayTeam());
        matchRepository.save(match);
        return match;
    }
}
