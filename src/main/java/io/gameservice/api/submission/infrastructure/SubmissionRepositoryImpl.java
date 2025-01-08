package io.gameservice.api.submission.infrastructure;

import io.gameservice.api.submission.infrastructure.entity.Submission;
import io.gameservice.api.submission.infrastructure.entity.SubmissionId;
import io.gameservice.api.submission.infrastructure.jdbc.SubmissionJdbcTemplateRepository;
import io.gameservice.api.submission.infrastructure.jpa.SubmissionJpaRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 7.
 */
@Repository
@Primary
@RequiredArgsConstructor
public class SubmissionRepositoryImpl implements SubmissionRepository {

    private final SubmissionJpaRepository submissionJpaRepository;
    private final SubmissionJdbcTemplateRepository submissionJdbcTemplateRepository;

    @Override
    public Submission save(Submission submission) {
        return submissionJpaRepository.save(submission);
    }

    @Override
    public Optional<Submission> findById(long partyId, long roundCount, long userId, int submissionCount) {
        return submissionJpaRepository.findById(SubmissionId.from(partyId, roundCount, userId, submissionCount));
    }

    public List<Submission> findAllByPartyIdAndRoundCount(long partyId, long roundCount) {
        return submissionJpaRepository.findAllByPartyIdAndRoundCount(partyId, roundCount);
    }

    @Override
    public List<Submission> findLatestByPartyId(long partyId) {
        return submissionJdbcTemplateRepository.findLatestByPartyId(partyId);
    }

    @Override
    public List<Submission> findLastestByPartyIdAndRoundCount(long partyId, long roundCount) {
        return submissionJdbcTemplateRepository.findLastestByPartyIdAndRoundCount(partyId, roundCount);
    }

    @Override
    public Optional<Submission> findLastestByUserId(long userId) {
        return submissionJdbcTemplateRepository.findLastestByUserId(userId);
    }
}
