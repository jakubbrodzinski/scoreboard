package org.scoreboard.domain.match.start;

import lombok.RequiredArgsConstructor;
import org.scoreboard.domain.command.CommandHandler;
import org.scoreboard.domain.match.factory.MatchFactory;
import org.scoreboard.domain.match.model.Match;
import org.scoreboard.domain.match.repository.MatchRepository;

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
