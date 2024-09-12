package org.scoreboard.domain.match.update;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.scoreboard.domain.match.model.Match;
import org.scoreboard.domain.match.model.MatchScore;
import org.scoreboard.domain.match.model.TeamScore;
import org.scoreboard.domain.match.repository.MatchRepository;
import org.scoreboard.domain.match.repository.exception.MatchNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateScoreCommandHandlerTest {
    @Mock
    MatchRepository matchRepository;

    @InjectMocks
    UpdateScoreCommandHandler commandHandler;

    @Test
    void shouldThrowExceptionWhenNoMatchWithGivenId() {
        var command = new UpdateScoreCommand("non-existing-match-id", null, commandHandler);
        when(matchRepository.findById("non-existing-match-id"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(command::execute)
                .isInstanceOf(MatchNotFoundException.class);
    }

    @Test
    void shouldUpdateTheScore() {
        var command = new UpdateScoreCommand("match-id", new MatchScore(5, 2), commandHandler);

        when(matchRepository.findById("match-id"))
                .thenReturn(Optional.of(Match.builder()
                        .homeTeamScore(new TeamScore("home-team-id", 2))
                        .awayTeamScore(new TeamScore("away-team-id", 3))
                        .build()));

        var result = command.execute();

        assertThat(result.getHomeTeamScore()).isEqualTo(new TeamScore("home-team-id", 5));
        assertThat(result.getAwayTeamScore()).isEqualTo(new TeamScore("away-team-id", 2));
    }

    @Test
    void shouldSaveUpdatedMatch() {
        var command = new UpdateScoreCommand("match-id", new MatchScore(1, 1), commandHandler);

        when(matchRepository.findById("match-id"))
                .thenReturn(Optional.of(Match.builder()
                        .homeTeamScore(new TeamScore("dummy-team"))
                        .awayTeamScore(new TeamScore("dummy-team"))
                        .build()));

        var result = command.execute();

        verify(matchRepository).update(same(result));
    }
}