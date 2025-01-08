package io.gameservice.api.submission.infrastructure.entity;

import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 11. 29.
 */
public class SubmissionIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Submission submission = (Submission) object;
        String query = "SELECT COALESCE(MAX(s.submissionCount), 0) + 1 FROM Submission s WHERE s.partyId = :partyId AND s.roundCount = :roundCount AND s.userId = :userId";
        Integer nextId = (Integer) session.createQuery(query)
                .setParameter("partyId", submission.getPartyId())
                .setParameter("roundCount", submission.getRoundCount())
                .setParameter("userId", submission.getUserId())
                .uniqueResult();
        return nextId;
    }
}