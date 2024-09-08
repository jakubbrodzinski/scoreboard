package org.scoreboard.match.repository;

import org.scoreboard.match.model.Match;

import java.util.List;
import java.util.Optional;

//todo removeById shouldThrow exception when there is no mathc w/ given id?
public interface MatchRepository {
    void save(Match match);
    Optional<Match> findById(String matchId);
    Match removeById(String matchId);
    List<Match> getMatches();
}
