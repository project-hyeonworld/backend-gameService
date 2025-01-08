package io.gameservice.api.game.strategy;

import io.gameservice.api.game.domain.dto.in.CheckConfirmCommand;
import io.gameservice.api.game.domain.dto.out.CheckInfo;
import java.util.Map;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 11.
 */
public interface GameStrategy<AnswerType, CheckInfoType extends CheckInfo> {
  long getGameId();
  long checkConfirm(CheckConfirmCommand command);
  long updateTargetId(long partyId, long roundCount, long targetId);
  void tutorialStage();
  String showStage(long partyId, long roundCount, long targetId);
  AnswerType playStage(long roundCount, long userId, String answer);

  CheckInfoType checkStage(long roundCount, long id);

  Map<Long, String> resultStage(String answer, long partyId, long roundCount);

  String getAnswer(long partyId, long roundCount, long targetId);
}
