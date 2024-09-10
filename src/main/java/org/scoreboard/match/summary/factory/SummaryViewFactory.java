package org.scoreboard.match.summary.factory;

import org.scoreboard.match.model.Match;
import org.scoreboard.match.summary.model.MatchSummary;
import org.scoreboard.match.summary.model.SummaryView;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class SummaryViewFactory {
    public SummaryView create(List<Match> matches) {
        return IntStream.range(0, matches.size())
                .mapToObj(index -> create(index, matches.get(index)))
                .collect(collectingAndThen(toList(), SummaryView::new));
    }

    private MatchSummary create(int position, Match match) {
        return MatchSummary.builder()
                .matchId(match.getMatchId())
                .position(position)
                .matchSummary(matchSummary(match))
                .build();
    }

    private String matchSummary(Match match) {
        var homeTeamScore = match.getHomeTeamScore();
        var awayTeamScore = match.getAwayTeamScore();
        return "%s %d - %s %d".formatted(homeTeamScore.team(), homeTeamScore.score(), awayTeamScore.team(), awayTeamScore.score());
    }
}
