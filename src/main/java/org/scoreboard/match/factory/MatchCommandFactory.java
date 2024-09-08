package org.scoreboard.match.factory;

import lombok.RequiredArgsConstructor;
import org.scoreboard.command.Commnad;
import org.scoreboard.match.finish.FinishMatchCommand;
import org.scoreboard.match.finish.FinishMatchCommandHandler;
import org.scoreboard.match.model.Match;
import org.scoreboard.match.model.MatchScore;
import org.scoreboard.match.repository.MatchRepository;
import org.scoreboard.match.start.StarMatchCommand;
import org.scoreboard.match.start.StartMatchCommandHandler;
import org.scoreboard.match.update.UpdateScoreCommand;
import org.scoreboard.match.update.UpdateScoreCommandHandler;

@RequiredArgsConstructor
public class MatchCommandFactory {
    private final MatchRepository matchRepository;

    Commnad<Match> startMatch(String homeTeam, String awayTeam) {
        return new StarMatchCommand(homeTeam, awayTeam, new StartMatchCommandHandler(matchRepository));
    }

    Commnad<Match> updateMatchScore(String matchId, int homeTeamScore, int awayTeamScore) {
        return new UpdateScoreCommand(
                new MatchScore(matchId, homeTeamScore, awayTeamScore),
                new UpdateScoreCommandHandler(matchRepository));
    }

    Commnad<Match> finishMatch(String matchId) {
        return new FinishMatchCommand(matchId, new FinishMatchCommandHandler(matchRepository));
    }
}
