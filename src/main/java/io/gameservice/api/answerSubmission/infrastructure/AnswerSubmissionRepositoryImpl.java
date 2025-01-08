package io.gameservice.api.answerSubmission.infrastructure;

import io.gameservice.api.answerSubmission.infrastructure.entity.AnswerSubmission;
import io.gameservice.api.answerSubmission.infrastructure.jdbc.AnswerSubmissionJdbctemplateRepository;
import io.gameservice.api.answerSubmission.infrastructure.jpa.AnswerSubmissionJpaRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 12.
 */
@Repository
@Primary
@RequiredArgsConstructor
public class AnswerSubmissionRepositoryImpl implements AnswerSubmissionRepository {
    private final AnswerSubmissionJdbctemplateRepository answerSubmissionJdbctemplateRepository;
    private final AnswerSubmissionJpaRepository answerSubmissionJpaRepository;

    @Override
    public AnswerSubmission saveAnswer(AnswerSubmission answerSubmission) {
        return answerSubmissionJpaRepository.save(answerSubmission);
    }

    @Override
    public List<AnswerSubmission> findAnswerMostRecentByRoundId(long roundId) {
        return answerSubmissionJdbctemplateRepository.findAnswerMostRecentByRoundId(roundId);
    }

    @Override
    public Optional<AnswerSubmission> findByRoundIdAndUserId(long roundId, long userId) {
        return answerSubmissionJdbctemplateRepository.findByRoundIdAndUserId(roundId, userId);
    }

    @Override
    public List<AnswerSubmission> findLatestByRoundIdAndAnswer(long roundId, String answer) {
        return answerSubmissionJdbctemplateRepository.findLatestByRoundIdAndAnswer(roundId, answer);
    }
}
