package org.scoreboard.match.summary.model;

import lombok.Builder;

@Builder
public record MatchSummary(String matchId, int position, String matchSummary) {

}
