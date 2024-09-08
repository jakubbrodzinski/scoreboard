package org.scoreboard.match.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@RequiredArgsConstructor
@Getter
@Builder
public class Match {
    private final String matchId;
    private final Instant creationDateTime;
    private final TeamScore homeTeamScore;
    private final TeamScore awayTeamScore;
}
