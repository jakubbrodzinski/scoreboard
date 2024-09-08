package org.scoreboard.command;

public interface CommandHandler<C extends Commnad<R>, R> {
    R handle(C command);
}
