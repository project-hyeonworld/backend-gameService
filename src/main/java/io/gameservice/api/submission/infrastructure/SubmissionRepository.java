package io.gameservice.api.submission.infrastructure;

import io.gameservice.api.submission.infrastructure.entity.Submission;
import java.util.List;
import java.util.Optional;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 7.
 */
public interface SubmissionRepository {

    Submission save(Submission submission);

    List<Submission> findLatestByPartyId(long roundCount);

    List<Submission> findLastestByPartyIdAndRoundCount(long partyId, long roundCount);

    Optional<Submission> findLastestByUserId(long userId);

    Optional<Submission> findById(long partyId, long roundCount, long userId, int submissionCount);
}
