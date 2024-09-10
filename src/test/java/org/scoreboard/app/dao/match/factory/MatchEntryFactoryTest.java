package org.scoreboard.app.dao.match.factory;

import org.junit.jupiter.api.Test;
import org.scoreboard.domain.match.model.Match;
import org.scoreboard.domain.match.model.TeamScore;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class MatchEntryFactoryTest {
    private final MatchEntryFactory factory = new MatchEntryFactory();

    @Test
    void shouldCreateMatchEntryObject() {
        var match = Match.builder()
                .matchId("m-id")
                .homeTeamScore(new TeamScore("dummy-team", 5))
                .awayTeamScore(new TeamScore("dummy-team", 13))
                .creationDateTime(Instant.parse("2023-12-13T13:15:01Z"))
                .build();

        var matchDao = factory.create(match);

        assertThat(matchDao.getMatch()).isEqualTo(match);
        assertThat(matchDao.getSortKey()).isEqualTo("0000000018#2023-12-13T13:15:01Z#m-id");
    }

    @Test
    void shouldExtractMatchId(){
        var result = factory.extractMatchId("<score>#<creation-timestamp>#match-id");

        assertThat(result).isEqualTo("match-id");
    }
}