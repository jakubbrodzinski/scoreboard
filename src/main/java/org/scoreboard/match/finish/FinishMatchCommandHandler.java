package org.scoreboard.match.finish;

import lombok.RequiredArgsConstructor;
import org.scoreboard.command.CommandHandler;
import org.scoreboard.match.model.Match;
import org.scoreboard.match.repository.MatchRepository;

@RequiredArgsConstructor
public class FinishMatchCommandHandler implements CommandHandler<FinishMatchCommand, Match> {
    private final MatchRepository matchRepository;
    @Override
    public Match handle(FinishMatchCommand command) {
        return null;
    }
}
