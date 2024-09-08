package org.scoreboard.match.start;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.scoreboard.match.factory.MatchFactory;
import org.scoreboard.match.model.Match;
import org.scoreboard.match.repository.MatchRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StartMatchCommandHandlerTest {
    @Mock
    private MatchFactory matchFactory;
    @Mock
    private MatchRepository matchRepository;

    @InjectMocks
    StartMatchCommandHandler commandHandler;

    @Test
    void shouldStartMatch() {
        var command = new StarMatchCommand("home-team", "away-team", commandHandler);

        var match = Match.builder().matchId("match-id").build();
        when(matchFactory.create("home-team", "away-team"))
                .thenReturn(match);

        var result = command.execute();

        assertThat(result).isSameAs(match);
        verify(matchRepository).save(same(match));
    }
}