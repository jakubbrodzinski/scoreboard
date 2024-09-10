package org.scoreboard.match.factory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.scoreboard.match.finish.FinishMatchCommand;
import org.scoreboard.match.finish.FinishMatchCommandHandler;
import org.scoreboard.match.model.MatchScore;
import org.scoreboard.match.repository.MatchRepository;
import org.scoreboard.match.start.StarMatchCommand;
import org.scoreboard.match.start.StartMatchCommandHandler;
import org.scoreboard.match.summary.SummaryViewCommand;
import org.scoreboard.match.summary.SummaryViewCommandHandler;
import org.scoreboard.match.update.UpdateScoreCommand;
import org.scoreboard.match.update.UpdateScoreCommandHandler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.type;

@ExtendWith(MockitoExtension.class)
class MatchCommandFactoryTest {
    @Mock
    MatchRepository matchRepository;

    @InjectMocks
    MatchCommandFactory factory;

    @Test
    void shouldCreateStartMatchCommand() {
        var result = factory.startMatch("home-team", "away-team");

        assertThat(result)
                .asInstanceOf(type(StarMatchCommand.class))
                .satisfies(command -> {
                    assertThat(command.getHomeTeam()).isEqualTo("home-team");
                    assertThat(command.getAwayTeam()).isEqualTo("away-team");
                    assertThat(command.getCommandHandler()).isInstanceOf(StartMatchCommandHandler.class);
                });
    }

    @Test
    void shouldCreateUpdateMatchScoreCommand() {
        var result = factory.updateMatchScore("match-id", 5, 2);

        assertThat(result)
                .asInstanceOf(type(UpdateScoreCommand.class))
                .satisfies(command -> {
                    assertThat(command.getMatchId()).isEqualTo("match-id");
                    assertThat(command.getMatchScore()).isEqualTo(new MatchScore(5, 2));
                    assertThat(command.getCommandHandler()).isInstanceOf(UpdateScoreCommandHandler.class);
                });
    }

    @Test
    void shouldCreateFinishMatchCommand() {
        var result = factory.finishMatch("match-id");

        assertThat(result)
                .asInstanceOf(type(FinishMatchCommand.class))
                .satisfies(command -> {
                    assertThat(command.getMatchId()).isEqualTo("match-id");
                    assertThat(command.getCommandHandler()).isInstanceOf(FinishMatchCommandHandler.class);
                });
    }

    @Test
    void shouldCreateSummaryViewCommand() {
        var result = factory.summaryView();

        assertThat(result)
                .asInstanceOf(type(SummaryViewCommand.class))
                .satisfies(command -> {
                    assertThat(command.getCommandHandler()).isInstanceOf(SummaryViewCommandHandler.class);
                });
    }
}