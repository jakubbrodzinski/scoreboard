package org.scoreboard.domain.match.factory;

import org.scoreboard.domain.match.model.Match;
import org.scoreboard.domain.match.model.TeamScore;

import java.util.UUID;

import static java.time.Instant.now;

public class MatchFactory {
    public Match create(String homeTeam, String awayTeam){
        return Match.builder()
                .matchId(UUID.randomUUID().toString())
                .homeTeamScore(new TeamScore(homeTeam))
                .awayTeamScore(new TeamScore(awayTeam))
                .creationDateTime(now())
                .build();
    }
}
