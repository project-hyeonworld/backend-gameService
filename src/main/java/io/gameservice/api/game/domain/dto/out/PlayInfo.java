package io.gameservice.api.game.domain.dto.out;

import io.gameservice.api.round.domain.dto.in.RoundPlayCommand;
import io.gameservice.common.mapper.ObjectrMapper;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
@Getter
public class PlayInfo {
  private long userId;
  public static PlayInfo from(RoundPlayCommand command) {
    return ObjectrMapper.convert(command, PlayInfo.class);
  }
}
