package io.gameservice.api.answerSubmission.infrastructure;

import io.gameservice.api.answerSubmission.infrastructure.entity.AnswerSubmission;
import java.util.List;
import java.util.Optional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 12.
 */
public interface AnswerSubmissionRepository {

    AnswerSubmission saveAnswer(AnswerSubmission answerSubmission);

    List<AnswerSubmission> findAnswerMostRecentByRoundId(long roundId);

    Optional<AnswerSubmission> findByRoundIdAndUserId(long roundId, long userId);

    List<AnswerSubmission> findLatestByRoundIdAndAnswer(long roundId, String answer);
}
