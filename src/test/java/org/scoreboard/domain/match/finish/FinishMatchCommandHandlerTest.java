package org.scoreboard.domain.match.finish;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.scoreboard.domain.match.model.Match;
import org.scoreboard.domain.match.repository.MatchRepository;
import org.scoreboard.domain.match.repository.exception.MatchNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FinishMatchCommandHandlerTest {
    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    FinishMatchCommandHandler commandHandler;

    @Test
    void shouldFinishMatch() {
        var command = new FinishMatchCommand("match-id", commandHandler);

        var match = Match.builder().matchId("match-id").build();
        when(matchRepository.removeById("match-id"))
                .thenReturn(match);

        var result = command.execute();

        assertThat(result).isSameAs(match);
    }

    @Test
    void shouldThrowExceptionWhenMatchRemovalFails() {
        var command = new FinishMatchCommand("invalid-match-id", commandHandler);

        when(matchRepository.removeById("invalid-match-id"))
                .thenThrow(new MatchNotFoundException("invalid-match-id"));

        assertThatThrownBy(command::execute)
                .isInstanceOf(MatchNotFoundException.class)
                .hasMessageContaining("invalid-match-id");
    }
}