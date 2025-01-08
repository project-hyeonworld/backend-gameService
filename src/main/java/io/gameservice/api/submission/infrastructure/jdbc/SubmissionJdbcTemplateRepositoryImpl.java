package io.gameservice.api.submission.infrastructure.jdbc;

import io.gameservice.api.submission.infrastructure.entity.Submission;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 8.
 */
@Repository
@RequiredArgsConstructor
public class SubmissionJdbcTemplateRepositoryImpl implements SubmissionJdbcTemplateRepository {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public Submission save(Submission submission) {
        return null;
    }

    @Override
    public List<Submission> findLatestByPartyId(long partyId) {
        String sql = """
                    SELECT s.*
                    FROM submission s
                    INNER JOIN (
                        SELECT user_id, MAX(created_at) as max_created_at
                        FROM submission
                        WHERE party_id = ?
                        GROUP BY user_id
                    ) latest ON s.user_id = latest.user_id AND s.created_at = latest.max_created_at
                    WHERE s.party_id = ?
                """;
        return jdbcTemplate.query(
                sql,
                new Long[]{partyId, partyId},
                (resultSet, rowNum) -> createToCheck(resultSet));
    }

    @Override
    public List<Submission> findLastestByPartyIdAndRoundCount(long partyId, long roundCount) {
        String sql = """
                    SELECT s.*
                    FROM submission s
                    INNER JOIN (
                        SELECT user_id, MAX(created_at) as max_created_at
                        FROM submission
                        WHERE party_id = ? AND round_id <= ?
                        GROUP BY user_id
                    ) latest ON s.user_id = latest.user_id AND s.created_at = latest.max_created_at
                    WHERE s.round_id <= ?
                """;
        return jdbcTemplate.query(
                sql,
                new Long[]{partyId, roundCount, roundCount},
                (resultSet, rowNum) -> createToCheck(resultSet));
    }

    @Override
    public Optional<Submission> findLastestByUserId(long userId) {
        String sql = """
                    SELECT s.*
                    FROM submission s
                    WHERE s.user_id = ?
                    AND s.created_at = (
                        SELECT MAX(created_at)
                        FROM submission
                        WHERE user_id = ?
                    )
                    LIMIT 1;
                """;
        List<Submission> submissions = jdbcTemplate.query(
                sql,

                (resultSet, rowNum) -> createToShow(resultSet),
                userId, userId);
        return submissions.stream().findFirst();
    }

    @Override
    public Optional<Submission> findById(long partyId, long roundCount, long userId, int submissionCount) {
        return Optional.empty();
    }

    private Submission createToCheck(ResultSet rs) throws SQLException {
        return Submission.defaultBuilder()
                .userId(rs.getLong("user_id"))
                .text(rs.getString("text"))
                .number(rs.getLong("number"))
                .build();
    }

    private Submission createToShow(ResultSet rs) throws SQLException {
        return Submission.defaultBuilder()
                .partyId(rs.getLong("party_id"))
                .roundCount(rs.getLong("round_count"))
                .userId(rs.getLong("user_id"))
                .submissionCount(rs.getInt("submission_count"))
                .text(rs.getString("text"))
                .number(rs.getLong("number"))
                .build();
    }
}
