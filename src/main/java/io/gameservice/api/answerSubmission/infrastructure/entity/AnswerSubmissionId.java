package io.gameservice.api.answerSubmission.infrastructure.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 27.
 */
class AnswerSubmissionId implements Serializable {
    private Long roundId;
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerSubmissionId that = (AnswerSubmissionId) o;
        return Objects.equals(roundId, that.roundId) &&
                        Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundId, userId);
    }
}