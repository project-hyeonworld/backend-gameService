package io.gameservice.api.submission.infrastructure.entity;

import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 29.
 */
@Getter
public class SubmissionId implements Serializable {

    private Long partyId;
    private Long roundCount;
    private Long userId;
    private Integer submissionCount;

    protected SubmissionId() {
    }

    private SubmissionId(long partyId, long roundCount, long userId, int submissionCount) {
        this.partyId = partyId;
        this.roundCount = roundCount;
        this.userId = userId;
        this.submissionCount = submissionCount;
    }

    public static SubmissionId from(long partyId, long roundCount, long userId, int submissionCount) {
        return new SubmissionId(partyId, roundCount, userId, submissionCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SubmissionId submissionId = (SubmissionId) o;
        return Objects.equals(partyId, submissionId.partyId) &&
                Objects.equals(roundCount, submissionId.roundCount) &&
                Objects.equals(userId, submissionId.userId) &&
                Objects.equals(submissionCount, getSubmissionCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(partyId, roundCount, userId, submissionCount);
    }
}
