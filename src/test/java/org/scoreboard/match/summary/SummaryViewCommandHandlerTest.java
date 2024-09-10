package org.scoreboard.match.summary;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.scoreboard.match.model.Match;
import org.scoreboard.match.repository.MatchRepository;
import org.scoreboard.match.summary.factory.SummaryViewFactory;
import org.scoreboard.match.summary.model.MatchSummary;
import org.scoreboard.match.summary.model.SummaryView;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SummaryViewCommandHandlerTest {
    @Mock
    MatchRepository matchRepository;
    @Mock
    SummaryViewFactory summaryViewFactory;

    @InjectMocks
    SummaryViewCommandHandler commandHandler;

    @Test
    void shouldReturnSummaryView() {
        var command = new SummaryViewCommand(commandHandler);

        var rankedMatches = List.of(
                Match.builder().matchId("match-1").build(),
                Match.builder().matchId("match-2").build());
        when(matchRepository.getRankedMatches())
                .thenReturn(rankedMatches);

        var summaryView = new SummaryView(List.of(MatchSummary.builder().build()));
        when(summaryViewFactory.create(rankedMatches))
                .thenReturn(summaryView);

        var result = command.execute();

        assertThat(result).isSameAs(summaryView);
    }

}