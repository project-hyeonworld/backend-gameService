package io.gameservice.api.submission.infrastructure.jpa;

import io.gameservice.api.submission.infrastructure.entity.Submission;
import io.gameservice.api.submission.infrastructure.entity.SubmissionId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 7.
 */
public interface SubmissionJpaRepository extends JpaRepository<Submission, SubmissionId> {

    List<Submission> findAllByPartyIdAndRoundCount(Long partyId, Long roundCount);
}
