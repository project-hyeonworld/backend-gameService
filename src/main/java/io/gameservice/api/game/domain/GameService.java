package io.gameservice.api.game.domain;

import io.gameservice.api.game.domain.dto.in.CheckStageCommand;
import io.gameservice.api.game.domain.dto.in.ResultStageCommand;
import io.gameservice.api.game.domain.dto.in.ShowStageCommand;
import io.gameservice.api.game.domain.dto.out.GameInfos;
import io.gameservice.api.game.domain.dto.out.ResultInfo;
import io.gameservice.api.game.infrastructure.GameRepository;
import io.gameservice.api.game.strategy.GameFactory;
import io.gameservice.api.game.strategy.GameStrategy;
import io.gameservice.api.game.domain.dto.out.CheckInfo;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
@Service
@RequiredArgsConstructor
public class GameService {
    private final GameFactory gameFactory;
    private final GameRepository gameRepository;

  public GameInfos displayGame() {
    return GameInfos.from(gameRepository.findByPlayable(true));
  }

    @Cacheable(cacheNames = "gameStrategy", key = "#gameId")
    public GameStrategy<?, ? extends CheckInfo> getGame(long gameId) {
        return gameFactory.getStrategy(gameId);
    }

    public CheckInfo checkStage(CheckStageCommand command) {
        GameStrategy<?, ? extends CheckInfo> gameStrategy = getGame(command.gameId());
        return gameStrategy.checkStage(command.partyId(), command.roundCount());
    }

    public String showStage(ShowStageCommand command) {
        GameStrategy<?, ? extends CheckInfo> gameStrategy = getGame(command.gameId());
        return gameStrategy.showStage(command.partyId(), command.roundCount(), command.targetId());
    }

    public ResultInfo resultStage(ResultStageCommand command) {
        GameStrategy<?, ? extends CheckInfo> gameStrategy = getGame(command.gameId());
        String answer = gameStrategy.getAnswer(command.partyId(), command.roundCount(), command.targetId());
        gameStrategy.resultStage(answer, command.partyId(), command.roundCount());
        return ResultInfo.from(answer, null);
    }
}
