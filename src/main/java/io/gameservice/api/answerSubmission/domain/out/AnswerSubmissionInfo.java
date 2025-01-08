package io.gameservice.api.answerSubmission.domain.out;

import io.gameservice.api.answerSubmission.infrastructure.entity.AnswerSubmission;
import io.gameservice.common.mapper.ObjectrMapper;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
@Getter
public class AnswerSubmissionInfo {
  private Long id;
  private Long roundId;
  private Long userId;
  private String answer;

  public static AnswerSubmission toEntity(long roundId, long userid, String answer){
    return AnswerSubmission.builder()
            .roundId(roundId)
            .userId(userid)
            .answer(answer)
            .build();
  }

  public static AnswerSubmissionInfo from(AnswerSubmission answerSubmission) {
    return ObjectrMapper.convert(answerSubmission, AnswerSubmissionInfo.class);
  }

  public boolean matchAnswer (String actualAnswer) {
    return answer.equals(actualAnswer);
  }
}
