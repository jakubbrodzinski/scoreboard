package org.scoreboard.app.dao.match;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.scoreboard.domain.match.model.Match;
import org.scoreboard.domain.match.model.TeamScore;
import org.scoreboard.domain.match.repository.exception.DuplicatedMatchException;
import org.scoreboard.domain.match.repository.exception.MatchNotFoundException;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InMemoryMatchRepositoryTest {
    private InMemoryMatchRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryMatchRepository();
    }

    @Nested
    class FindById {
        @Test
        void shouldReturnEmptyOptionalWhenNoMatchMatchingId() {
            //given
            repository.save(match("match-id", "2012-01-01T12:00:00Z"));

            //when
            var result = repository.findById("different-match-id");

            //then
            assertThat(result).isEmpty();
        }

        @Test
        void shouldFindMatchById() {
            //given
            var match = Match.builder()
                    .matchId("26df8463-1326-4acb-b8f4-d63ab8c17817")
                    .creationDateTime(Instant.now())
                    .homeTeamScore(new TeamScore("home team", 5))
                    .awayTeamScore(new TeamScore("away team", 2))
                    .build();
            repository.save(match);

            //when
            var result = repository.findById("26df8463-1326-4acb-b8f4-d63ab8c17817");

            //then
            assertThat(result).hasValue(match);
        }
    }

    @Nested
    class RemoveById {
        @Test
        void shouldThrowExceptionWhenRemovingNonExistingMatch() {
            assertThatThrownBy(() -> repository.removeById("non-existing-match-id"))
                    .isInstanceOf(MatchNotFoundException.class)
                    .hasMessageContaining("non-existing-match-id");
        }

        @Test
        void shouldRemoveMatch() {
            //given
            var match = match("match-id", "2012-01-01T00:00:00Z");
            repository.save(match);

            //when
            var result = repository.removeById("match-id");

            //then
            assertThat(result).isEqualTo(match);
            assertThat(repository.findById("match-id")).isEmpty();
        }
    }

    @Nested
    class Save {
        @Test
        void shouldSaveMatch() {
            //given
            var match = Match.builder()
                    .matchId("26df8463-1326-4acb-b8f4-d63ab8c17817")
                    .creationDateTime(Instant.now())
                    .homeTeamScore(new TeamScore("home team", 5))
                    .awayTeamScore(new TeamScore("away team", 2))
                    .build();
            repository.save(match);

            //when
            var result = repository.findById("26df8463-1326-4acb-b8f4-d63ab8c17817");

            //then
            assertThat(result).hasValue(match);
        }

        @Test
        void shouldThrowExceptionWhenSavingMatchWithDuplicatedId() {
            //given
            repository.save(match("duplicated-match-id", "2012-01-01T12:00:00Z"));

            //when & then
            assertThatThrownBy(() -> repository.save(match("duplicated-match-id", "2012-01-01T15:00:00Z")))
                    .isInstanceOf(DuplicatedMatchException.class)
                    .hasMessageContaining("duplicated-match-id");
        }
    }

    @Nested
    class GetRankedMatches {
        @Test
        void shouldReturnMatchesInRankedOrder() {
            repository.save(match("match-with-score-5", "2012-01-01T12:00:00Z", 3, 2));
            repository.save(match("newer-match-with-score-5", "2020-01-01T12:00:00Z", 2, 3));
            repository.save(match("match-with-biggest-total-score", "2020-01-01T12:00:00Z", 5, 5));

            assertThat(repository.getRankedMatches())
                    .extracting(Match::getMatchId)
                    .containsExactly("match-with-biggest-total-score", "newer-match-with-score-5", "match-with-score-5");
        }

        @Test
        void shouldReturnAllMatches() {
            //given
            var match = Match.builder()
                    .matchId("26df8463-1326-4acb-b8f4-d63ab8c17817")
                    .creationDateTime(Instant.now())
                    .homeTeamScore(new TeamScore("home team", 5))
                    .awayTeamScore(new TeamScore("away team", 2))
                    .build();
            repository.save(match);

            //when
            var result = repository.getRankedMatches();

            //then
            assertThat(result)
                    .singleElement()
                    .isEqualTo(match);
        }

        @Test
        void shouldReturnMatchWithBiggerIdWhenTotalScoreAndTimestampAreEqual() {
            repository.save(match("match-with-same-rank-1", "2012-01-01T12:00:00Z", 3, 2));
            repository.save(match("match-with-same-rank-2", "2022-01-01T12:00:00Z", 2, 3));

            assertThat(repository.getRankedMatches())
                    .extracting(Match::getMatchId)
                    .containsExactly("match-with-same-rank-2","match-with-same-rank-1");
        }
    }

    private Match match(String matchId, String creationDateTime) {
        return Match.builder()
                .matchId(matchId)
                .creationDateTime(Instant.parse(creationDateTime))
                .homeTeamScore(new TeamScore("dummy-team"))
                .awayTeamScore(new TeamScore("dummy-team"))
                .build();
    }

    private Match match(String matchId, String creationDateTime, int homeTeamScore, int awayTeamScore) {
        return Match.builder()
                .matchId(matchId)
                .creationDateTime(Instant.parse(creationDateTime))
                .homeTeamScore(new TeamScore("dummy-team", homeTeamScore))
                .awayTeamScore(new TeamScore("dummy-team", awayTeamScore))
                .build();
    }
}