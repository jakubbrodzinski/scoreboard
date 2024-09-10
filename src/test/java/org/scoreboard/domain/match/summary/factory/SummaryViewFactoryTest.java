package org.scoreboard.domain.match.summary.factory;

import org.junit.jupiter.api.Test;
import org.scoreboard.domain.match.model.Match;
import org.scoreboard.domain.match.model.TeamScore;
import org.scoreboard.domain.match.summary.factory.SummaryViewFactory;
import org.scoreboard.domain.match.summary.model.MatchSummary;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class SummaryViewFactoryTest {
    private final SummaryViewFactory factory = new SummaryViewFactory();

    @Test
    void shouldCreateMatchSummary() {
        var match = Match.builder()
                .homeTeamScore(new TeamScore("team-1", 0))
                .awayTeamScore(new TeamScore("team-2", 10))
                .build();

        var result = factory.create(List.of(match));

        assertThat(result.matches())
                .singleElement()
                .extracting(MatchSummary::matchSummary)
                .isEqualTo("team-1 0 - team-2 10");
    }

    @Test
    void shouldAssignPositionBasedOnInputOrder() {
        var result = factory.create(List.of(match("match-0"), match("match-1"), match("match-2")));

        assertThat(result.matches())
                .extracting(MatchSummary::matchId, MatchSummary::position)
                .containsExactly(tuple("match-0",0),tuple("match-1",1),tuple("match-2",2));
    }

    private Match match(String matchId) {
        return Match.builder()
                .matchId(matchId)
                .homeTeamScore(new TeamScore(""))
                .awayTeamScore(new TeamScore(""))
                .build();
    }

}