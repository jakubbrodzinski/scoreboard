package org.scoreboard.domain.match.factory;

import org.junit.jupiter.api.Test;
import org.scoreboard.domain.match.factory.MatchFactory;

import java.util.UUID;

import static java.time.Instant.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class MatchFactoryTest {
    private final MatchFactory factory = new MatchFactory();

    @Test
    void shouldSetIdForMatch() {
        var result = factory.create("dummy-team", "dummy-team");

        assertThatCode(() -> UUID.fromString(result.getMatchId()))
                .doesNotThrowAnyException();
    }

    @Test
    void shouldSetScores() {
        var result = factory.create("dummy-team", "dummy-team");

        assertThat(result.getHomeTeamScore().score()).isEqualTo(0);
        assertThat(result.getAwayTeamScore().score()).isEqualTo(0);
    }

    @Test
    void shouldSetTeams() {
        var result = factory.create("home-team", "away-team");

        assertThat(result.getHomeTeamScore().team()).isEqualTo("home-team");
        assertThat(result.getAwayTeamScore().team()).isEqualTo("away-team");
    }

    @Test
    void shouldSetCreationTimestamp() {
        var beforeTimestamp = now();

        var result = factory.create("home-team", "away-team");

        assertThat(result.getCreationDateTime())
                .isAfter(beforeTimestamp)
                .isBefore(now());
    }
}