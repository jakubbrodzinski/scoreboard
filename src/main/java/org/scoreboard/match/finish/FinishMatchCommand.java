package org.scoreboard.match.finish;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.scoreboard.command.Commnad;
import org.scoreboard.match.model.Match;

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
