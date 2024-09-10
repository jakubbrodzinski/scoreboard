package org.scoreboard.app.dao.match.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.scoreboard.domain.match.model.Match;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class MatchEntry {
    public static final String SEPARATOR = "#";

    private final Match match;
    private final String sortKey;

    public String getId() {
        return match.getMatchId();
    }
}
