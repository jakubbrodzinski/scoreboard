package org.scoreboard.domain.match.summary;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.scoreboard.domain.command.Commnad;
import org.scoreboard.domain.match.summary.model.SummaryView;

@RequiredArgsConstructor
@Getter
public class SummaryViewCommand implements Commnad<SummaryView> {
    private final SummaryViewCommandHandler commandHandler;

    @Override
    public SummaryView execute() {
        return commandHandler.handle(this);
    }
}
