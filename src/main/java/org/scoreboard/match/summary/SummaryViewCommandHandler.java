package org.scoreboard.match.summary;

import lombok.RequiredArgsConstructor;
import org.scoreboard.command.CommandHandler;
import org.scoreboard.match.repository.MatchRepository;
import org.scoreboard.match.summary.factory.SummaryViewFactory;
import org.scoreboard.match.summary.model.SummaryView;

@RequiredArgsConstructor
public class SummaryViewCommandHandler implements CommandHandler<SummaryViewCommand, SummaryView> {
    private final MatchRepository matchRepository;
    private final SummaryViewFactory summaryViewFactory;

    @Override
    public SummaryView handle(SummaryViewCommand command) {
        return summaryViewFactory.create(matchRepository.getRankedMatches());
    }
}
