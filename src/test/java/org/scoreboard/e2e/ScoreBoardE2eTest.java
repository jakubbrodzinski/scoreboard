package org.scoreboard.e2e;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.scoreboard.app.dao.match.InMemoryMatchRepository;
import org.scoreboard.domain.match.factory.MatchCommandFactory;
import org.scoreboard.domain.match.factory.MatchFactory;
import org.scoreboard.domain.match.summary.factory.SummaryViewFactory;
import org.scoreboard.domain.match.summary.model.MatchSummary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

public class ScoreBoardE2eTest {
    private MatchCommandFactory commandFactory;

    @BeforeEach
    void setUp() {
        commandFactory = new MatchCommandFactory(
                new MatchFactory(),
                new InMemoryMatchRepository(),
                new SummaryViewFactory());
    }

    @Test
    void testMainScenario() {
        var germanyFranceMatch = commandFactory.startMatch("Germany", "France")
                .execute();
        var mexicoCanadaMatch = commandFactory.startMatch("Mexico", "Canada")
                .execute();
        var spainBrazilMatch = commandFactory.startMatch("Spain", "Brazil")
                .execute();
        var uruguayItalyMatch = commandFactory.startMatch("Uruguay", "Italy")
                .execute();
        var argentinaAustraliaMatch = commandFactory.startMatch("Argentina", "Australia")
                .execute();

        commandFactory.updateMatchScore(mexicoCanadaMatch.getMatchId(), 2, 2)
                .execute();
        commandFactory.updateMatchScore(argentinaAustraliaMatch.getMatchId(), 3, 1)
                .execute();
        commandFactory.updateMatchScore(uruguayItalyMatch.getMatchId(), 6, 6)
                .execute();
        commandFactory.updateMatchScore(germanyFranceMatch.getMatchId(), 2, 2)
                .execute();
        commandFactory.updateMatchScore(spainBrazilMatch.getMatchId(), 10, 2)
                .execute();
        commandFactory.updateMatchScore(mexicoCanadaMatch.getMatchId(), 0, 5)
                .execute();

        var boardSummary = commandFactory.summaryView().execute();

        assertThat(boardSummary.matches())
                .extracting(MatchSummary::position, MatchSummary::matchSummary)
                .containsExactly(tuple(1, "Uruguay 6 - Italy 6"),
                        tuple(2, "Spain 10 - Brazil 2"),
                        tuple(3, "Mexico 0 - Canada 5"),
                        tuple(4, "Argentina 3 - Australia 1"),
                        tuple(5, "Germany 2 - France 2"));
    }

    @Test
    void testRemoval() {
        var match = commandFactory.startMatch("Mexico", "Canada")
                .execute();
        commandFactory.updateMatchScore(match.getMatchId(), 2, 2)
                .execute();
        commandFactory.finishMatch(match.getMatchId())
                .execute();

        var boardSummary = commandFactory.summaryView().execute();

        assertThat(boardSummary.matches()).isEmpty();
    }

    @Test
    void testUpdate() {
        var match = commandFactory.startMatch("Mexico", "Canada")
                .execute();
        commandFactory.updateMatchScore(match.getMatchId(), 3, 1)
                .execute();

        var boardSummary = commandFactory.summaryView().execute();

        assertThat(boardSummary.matches())
                .singleElement()
                .isEqualTo(new MatchSummary(match.getMatchId(), 1, "Mexico 3 - Canada 1"));
    }

    /*
    * As an example, being the current data in the system:
1. Uruguay 6 - Italy 6
2. Spain 10 - Brazil 2
3. Mexico 0 - Canada 5
4. Argentina 3 - Australia 1
5. Germany 2 - France 2
    * */
}
