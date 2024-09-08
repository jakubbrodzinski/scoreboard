package org.scoreboard.match.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@RequiredArgsConstructor
@Getter
public class Match {
    private final String matchId;
    private final Instant creationDateTime;
    private final TeamScore homeTeamScore;
    private final TeamScore awayTeamScore;

    record TeamScore(String team, int score) {
        TeamScore(String team) {
            this(team, 0);
        }
    }
}
