package org.scoreboard.domain.match.factory;

import lombok.RequiredArgsConstructor;
import org.scoreboard.domain.command.Commnad;
import org.scoreboard.domain.match.finish.FinishMatchCommand;
import org.scoreboard.domain.match.finish.FinishMatchCommandHandler;
import org.scoreboard.domain.match.model.Match;
import org.scoreboard.domain.match.model.MatchScore;
import org.scoreboard.domain.match.repository.MatchRepository;
import org.scoreboard.domain.match.start.StarMatchCommand;
import org.scoreboard.domain.match.start.StartMatchCommandHandler;
import org.scoreboard.domain.match.summary.SummaryViewCommand;
import org.scoreboard.domain.match.summary.SummaryViewCommandHandler;
import org.scoreboard.domain.match.summary.factory.SummaryViewFactory;
import org.scoreboard.domain.match.summary.model.SummaryView;
import org.scoreboard.domain.match.update.UpdateScoreCommand;
import org.scoreboard.domain.match.update.UpdateScoreCommandHandler;

@RequiredArgsConstructor
public class MatchCommandFactory {
    private final MatchFactory matchFactory;
    private final MatchRepository matchRepository;
    private final SummaryViewFactory summaryViewFactory;

    public Commnad<Match> startMatch(String homeTeam, String awayTeam) {
        return new StarMatchCommand(homeTeam, awayTeam, new StartMatchCommandHandler(matchFactory, matchRepository));
    }

    public Commnad<Match> updateMatchScore(String matchId, int homeTeamScore, int awayTeamScore) {
        return new UpdateScoreCommand(
                matchId,
                new MatchScore(homeTeamScore, awayTeamScore),
                new UpdateScoreCommandHandler(matchRepository));
    }

    public Commnad<Match> finishMatch(String matchId) {
        return new FinishMatchCommand(matchId, new FinishMatchCommandHandler(matchRepository));
    }

    public Commnad<SummaryView> summaryView() {
        return new SummaryViewCommand(new SummaryViewCommandHandler(matchRepository, summaryViewFactory));
    }
}
