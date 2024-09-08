package org.scoreboard.match.repository;

import org.scoreboard.match.model.Match;

import java.util.List;
import java.util.Optional;

public interface MatchRepository {
    Optional<Match> findById(String matchId);
    void removeById(String matchId);
    List<Match> getMatches();
}
