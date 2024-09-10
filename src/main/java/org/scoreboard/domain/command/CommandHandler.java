package org.scoreboard.domain.command;

import org.scoreboard.domain.Commnad;

public interface CommandHandler<C extends Commnad<R>, R> {
    R handle(C command);
}
