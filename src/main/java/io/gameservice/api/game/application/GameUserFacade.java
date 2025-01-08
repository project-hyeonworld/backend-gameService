package io.gameservice.api.game.application;

import io.gameservice.api.game.client.user.UserClient;
import io.gameservice.api.game.domain.GameService;
import io.gameservice.api.game.domain.dto.in.ShowStageCommand;
import io.gameservice.common.annotation.Facade;
import lombok.RequiredArgsConstructor;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 25. 1. 8.
 */
@Facade
@RequiredArgsConstructor
public class GameUserFacade {
    private final GameService gameService;
    private final UserClient userClient;

}
