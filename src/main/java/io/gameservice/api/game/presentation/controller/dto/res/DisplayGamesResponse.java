package io.gameservice.api.game.presentation.controller.dto.res;

import io.gameservice.api.game.domain.dto.out.GameInfos;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
public record DisplayGamesResponse(GameInfos games) implements GameResponse {

  public static DisplayGamesResponse from (GameInfos gameInfos){
    return new DisplayGamesResponse(gameInfos);
  }
}
