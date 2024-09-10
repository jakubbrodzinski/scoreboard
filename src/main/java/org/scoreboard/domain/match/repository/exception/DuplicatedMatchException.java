package org.scoreboard.domain.match.repository.exception;

public class DuplicatedMatchException extends RuntimeException{
    public DuplicatedMatchException(String matchId) {
        super("Match [%s] already exists".formatted(matchId));
    }
}
