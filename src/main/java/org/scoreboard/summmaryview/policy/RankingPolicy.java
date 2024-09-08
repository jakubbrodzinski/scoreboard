package org.scoreboard.summmaryview.policy;

import org.scoreboard.match.model.Match;

public interface RankingPolicy<T extends Comparable<T>> {
    T apply(Match match);
}
