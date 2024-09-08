package org.scoreboard.match.finish;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.scoreboard.match.model.Match;
import org.scoreboard.match.repository.MatchRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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
}