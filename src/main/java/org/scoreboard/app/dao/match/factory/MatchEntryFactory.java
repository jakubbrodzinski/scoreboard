package org.scoreboard.app.dao.match.factory;

import org.scoreboard.app.dao.match.model.MatchEntry;
import org.scoreboard.domain.match.model.Match;

import static org.scoreboard.app.dao.match.model.MatchEntry.SEPARATOR;

public class MatchEntryFactory {
    public MatchEntry create(Match match) {
        return new MatchEntry(match, sortKey(match));
    }

    private String sortKey(Match match) {
        return "%010d#%s#%s".formatted(
                totalScore(match),
                match.getCreationDateTime().toString(),
                match.getMatchId());
    }

    private int totalScore(Match match) {
        return match.getAwayTeamScore().score() + match.getHomeTeamScore().score();
    }

    public String extractMatchId(String sortKey) {
        return sortKey.substring(sortKey.lastIndexOf(SEPARATOR) + 1);
    }
}
