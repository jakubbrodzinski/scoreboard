package org.scoreboard.domain.match.repository.exception;

public class MatchNotFoundException extends RuntimeException{
    public MatchNotFoundException(String matchId){
        super("Match [%s] not found".formatted(matchId));
    }
}
