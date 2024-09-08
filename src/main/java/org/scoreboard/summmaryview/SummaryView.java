package org.scoreboard.summmaryview;

import java.util.List;

public record SummaryView(List<MatchSummary> matches) {

    record MatchSummary(String matchId, String position, String matchSummary){}
}
