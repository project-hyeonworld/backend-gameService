package io.gameservice.api.game.domain.dto.out;


import io.gameservice.api.user.application.dto.NameScoreDto;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 10. 1.
 */
public record RankInfo(
        PriorityQueue<NameScoreDto> participants
){


  public RankInfo(List<NameScoreDto> userScoreInfos) {
    this(new PriorityQueue<NameScoreDto>(
            (a, b) -> Long.compare(b.score(), a.score())
    ));
    participants.addAll(userScoreInfos);
  }

  public static RankInfo from(List<NameScoreDto> userScoreInfos) {
    return new RankInfo(userScoreInfos);
  }
}
