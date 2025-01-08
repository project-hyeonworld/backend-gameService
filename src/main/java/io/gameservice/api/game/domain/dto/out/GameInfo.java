package io.gameservice.api.game.domain.dto.out;

import io.gameservice.api.game.infrastructure.entity.Game;
import io.gameservice.common.mapper.ObjectrMapper;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 3.
 */
@Getter
public class GameInfo {
    long id;
    String name;
    String description;
  public static GameInfo from (Game game) {
    return ObjectrMapper.convert(game, GameInfo.class);
  }
}
