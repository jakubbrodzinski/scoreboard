package org.scoreboard.match.repository;

import org.scoreboard.match.model.Match;

import java.util.List;
import java.util.Optional;

public interface MatchRepository {
    void save(Match match);

    Optional<Match> findById(String matchId);

    Match removeById(String matchId);

    List<Match> getRankedMatches();
}
