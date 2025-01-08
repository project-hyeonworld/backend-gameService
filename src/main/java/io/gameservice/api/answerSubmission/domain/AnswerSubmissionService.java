package io.gameservice.api.answerSubmission.domain;

import io.gameservice.api.answerSubmission.domain.out.AnswerSubmissionInfo;
import io.gameservice.api.answerSubmission.domain.out.AnswerSubmissionInfos;
import io.gameservice.api.answerSubmission.infrastructure.AnswerSubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 12.
 */
@Service
@RequiredArgsConstructor
public class AnswerSubmissionService {
    private final AnswerSubmissionRepository answerSubmissionRepository;
    
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AnswerSubmissionInfo submitAnswer(long roundId, long userId, String answer) {
        return AnswerSubmissionInfo.from(
                answerSubmissionRepository.saveAnswer(AnswerSubmissionInfo.toEntity(roundId, userId, answer)));
    }

    public AnswerSubmissionInfos retrieveAnswerSubmissions(long roundId) {
        return AnswerSubmissionInfos.from(answerSubmissionRepository.findAnswerMostRecentByRoundId(roundId));
    }

    public AnswerSubmissionInfos findByAnswer(long roundId, String number) {
        return AnswerSubmissionInfos.from(answerSubmissionRepository.findLatestByRoundIdAndAnswer(roundId, number));
    }
}
