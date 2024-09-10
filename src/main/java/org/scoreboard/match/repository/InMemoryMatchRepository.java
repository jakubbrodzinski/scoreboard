package org.scoreboard.match.repository;

import org.scoreboard.match.model.Match;

import java.util.List;
import java.util.Optional;

//It's not really a part of domain.
//todo removeById shouldThrow exception when there is no mathc w/ given id?
//2. summaryview 1. this repo 3. e2e test
public class InMemoryMatchRepository implements MatchRepository {
    @Override
    public void save(Match match) {

    }

    @Override
    public Optional<Match> findById(String matchId) {
        return Optional.empty();
    }

    @Override
    public Match removeById(String matchId) {
        return null;
    }

    @Override
    public List<Match> getRankedMatches() {
        return List.of();
    }
}
