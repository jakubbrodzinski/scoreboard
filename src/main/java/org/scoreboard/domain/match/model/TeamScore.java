package org.scoreboard.domain.match.model;

public record TeamScore(String team, int score) {
    public TeamScore(String team) {
        this(team, 0);
    }

    TeamScore withScore(int score) {
        return new TeamScore(this.team, score);
    }
}
