package org.scoreboard.match.model;

public record TeamScore(String team, int score) {
    public TeamScore(String team) {
        this(team, 0);
    }
}
