package org.scoreboard.domain.match.finish;

import lombok.RequiredArgsConstructor;
import org.scoreboard.domain.command.CommandHandler;
import org.scoreboard.domain.match.model.Match;
import org.scoreboard.domain.match.repository.MatchRepository;

@RequiredArgsConstructor
public class FinishMatchCommandHandler implements CommandHandler<FinishMatchCommand, Match> {
    private final MatchRepository matchRepository;

    @Override
    public Match handle(FinishMatchCommand command) {
        return matchRepository.removeById(command.getMatchId());
    }
}
