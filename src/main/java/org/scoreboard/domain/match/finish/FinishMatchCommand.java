package org.scoreboard.domain.match.finish;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.scoreboard.domain.command.Commnad;
import org.scoreboard.domain.match.model.Match;

@RequiredArgsConstructor
@Getter
public class FinishMatchCommand implements Commnad<Match> {
    private final String matchId;
    private final FinishMatchCommandHandler commandHandler;

    @Override
    public Match execute() {
        return commandHandler.handle(this);
    }
}
