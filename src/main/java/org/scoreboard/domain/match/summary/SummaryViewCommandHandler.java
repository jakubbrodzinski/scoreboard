package org.scoreboard.domain.match.summary;

import lombok.RequiredArgsConstructor;
import org.scoreboard.domain.command.CommandHandler;
import org.scoreboard.domain.match.repository.MatchRepository;
import org.scoreboard.domain.match.summary.factory.SummaryViewFactory;
import org.scoreboard.domain.match.summary.model.SummaryView;

@RequiredArgsConstructor
public class SummaryViewCommandHandler implements CommandHandler<SummaryViewCommand, SummaryView> {
    private final MatchRepository matchRepository;
    private final SummaryViewFactory summaryViewFactory;

    @Override
    public SummaryView handle(SummaryViewCommand command) {
        return summaryViewFactory.create(matchRepository.getRankedMatches());
    }
}
