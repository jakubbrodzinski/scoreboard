package org.scoreboard.domain.match.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.scoreboard.domain.match.model.Match;
import org.scoreboard.domain.match.model.MatchScore;
import org.scoreboard.domain.match.model.TeamScore;

import static java.time.Instant.parse;
import static org.assertj.core.api.Assertions.assertThat;

class MatchTest {
    @Nested
    class UpdateScore {
        @Test
        void shouldNotChangeMatchId() {
            var match = Match.builder()
                    .matchId("match-id")
                    .awayTeamScore(new TeamScore("dummy-team"))
                    .homeTeamScore(new TeamScore("dummy-team"))
                    .build();

            var updatedMatch = match.updateScore(new MatchScore(1, 1));

            assertThat(updatedMatch.getMatchId()).isEqualTo("match-id");
        }

        @Test
        void shouldNotChangeMatchCreationDateTime() {
            var match = Match.builder()
                    .creationDateTime(parse("2024-01-01T12:15:00Z"))
                    .awayTeamScore(new TeamScore("dummy-team"))
                    .homeTeamScore(new TeamScore("dummy-team"))
                    .build();

            var updatedMatch = match.updateScore(new MatchScore(1, 1));

            assertThat(updatedMatch.getCreationDateTime()).hasToString("2024-01-01T12:15:00Z");
        }

        @Test
        void shouldUpdateAwayTeamScore() {
            var match = Match.builder()
                    .awayTeamScore(new TeamScore("away-team"))
                    .homeTeamScore(new TeamScore("dummy-team"))
                    .build();

            var updatedMatch = match.updateScore(new MatchScore(1, 5));

            assertThat(updatedMatch.getAwayTeamScore()).isEqualTo(new TeamScore("away-team", 5));
        }

        @Test
        void shouldUpdateHomeTeamScore() {
            var match = Match.builder()
                    .awayTeamScore(new TeamScore("dummy-team"))
                    .homeTeamScore(new TeamScore("home-team"))
                    .build();

            var updatedMatch = match.updateScore(new MatchScore(3, 1));

            assertThat(updatedMatch.getHomeTeamScore()).isEqualTo(new TeamScore("home-team", 3));
        }
    }
}