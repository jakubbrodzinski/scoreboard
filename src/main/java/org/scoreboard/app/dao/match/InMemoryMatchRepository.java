package org.scoreboard.app.dao.match;

import org.scoreboard.app.dao.match.factory.MatchEntryFactory;
import org.scoreboard.app.dao.match.model.MatchEntry;
import org.scoreboard.domain.match.model.Match;
import org.scoreboard.domain.match.repository.MatchRepository;
import org.scoreboard.domain.match.repository.exception.DuplicatedMatchException;
import org.scoreboard.domain.match.repository.exception.MatchNotFoundException;

import java.util.*;

import static java.util.Comparator.reverseOrder;

//todo e2e test
public class InMemoryMatchRepository implements MatchRepository {
    private final MatchEntryFactory matchEntryFactory = new MatchEntryFactory();
    private final Map<String, MatchEntry> DB = new HashMap<>();
    private final TreeSet<String> RANKING_INDEX = new TreeSet<>(reverseOrder());

    @Override
    public void save(Match match) {
        if (DB.containsKey(match.getMatchId())) {
            throw new DuplicatedMatchException(match.getMatchId());
        } else {
            var matchEntry = matchEntryFactory.create(match);
            DB.put(matchEntry.getId(), matchEntry);
            RANKING_INDEX.add(matchEntry.getSortKey());
        }
    }

    @Override
    public Optional<Match> findById(String matchId) {
        return Optional.ofNullable(DB.get(matchId))
                .map(MatchEntry::getMatch);
    }

    @Override
    public Match removeById(String matchId) {
        if (!DB.containsKey(matchId)) {
            throw new MatchNotFoundException(matchId);
        } else {
            var removedMatch = DB.remove(matchId);
            RANKING_INDEX.remove(removedMatch.getSortKey());
            return removedMatch.getMatch();
        }
    }

    @Override
    public List<Match> getRankedMatches() {
        return RANKING_INDEX.stream()
                .map(matchEntryFactory::extractMatchId)
                .map(DB::get)
                .map(MatchEntry::getMatch)
                .toList();
    }
}
