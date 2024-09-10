package org.scoreboard.match.update;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.scoreboard.command.Commnad;
import org.scoreboard.match.model.Match;
import org.scoreboard.match.model.MatchScore;

@RequiredArgsConstructor
@Getter
public class UpdateScoreCommand implements Commnad<Match> {
    private final String matchId;
    private final MatchScore matchScore;
    private final UpdateScoreCommandHandler commandHandler;

    @Override
    public Match execute() {
        return commandHandler.handle(this);
    }
}
