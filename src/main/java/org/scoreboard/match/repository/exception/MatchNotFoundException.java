package org.scoreboard.match.repository.exception;

public class MatchNotFoundException extends RuntimeException{
    public MatchNotFoundException(String matchId){
        super("Match [%s] not found".formatted(matchId));
    }
}
