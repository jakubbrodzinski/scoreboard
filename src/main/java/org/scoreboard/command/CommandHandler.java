package org.scoreboard.command;

import org.scoreboard.scoreboard.Commnad;

public interface CommandHandler<C extends Commnad<R>, R> {
    R handle(C command);
}
