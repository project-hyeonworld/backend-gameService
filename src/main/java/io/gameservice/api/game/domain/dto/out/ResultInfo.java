package io.gameservice.api.game.domain.dto.out;

import io.gameservice.common.dto.NameDtos;
import java.util.Map;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 16.
 */
public record ResultInfo(
    String answer,
    NameDtos nameDtos
){

  public static ResultInfo from(String answer, Map<Long, String> winnersIdName) {
    return new ResultInfo(answer, NameDtos.from(winnersIdName));
  }
}
