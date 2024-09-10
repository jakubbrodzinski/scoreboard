package org.scoreboard.domain.match.start;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.scoreboard.domain.command.Commnad;
import org.scoreboard.domain.match.model.Match;

@RequiredArgsConstructor
@Getter
public class StarMatchCommand implements Commnad<Match> {
    private final String homeTeam;
    private final String awayTeam;

    private final StartMatchCommandHandler commandHandler;

    @Override
    public Match execute() {
        return commandHandler.handle(this);
    }
}
