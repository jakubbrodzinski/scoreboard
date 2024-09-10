package org.scoreboard.match.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@RequiredArgsConstructor
@Getter
@Builder(toBuilder = true)
public class Match {
    private final String matchId;
    private final Instant creationDateTime;
    private final TeamScore homeTeamScore;
    private final TeamScore awayTeamScore;

    public Match updateScore(MatchScore matchScore){
        return this.toBuilder()
                .homeTeamScore(homeTeamScore.withScore(matchScore.homeTeamScore()))
                .awayTeamScore(awayTeamScore.withScore(matchScore.awayTeamScore()))
                .build();
    }
}
