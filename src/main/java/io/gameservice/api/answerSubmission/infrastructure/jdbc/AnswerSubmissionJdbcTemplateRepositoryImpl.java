package io.gameservice.api.answerSubmission.infrastructure.jdbc;

import io.gameservice.api.answerSubmission.infrastructure.entity.AnswerSubmission;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 16.
 */
@Repository
@RequiredArgsConstructor
public class AnswerSubmissionJdbcTemplateRepositoryImpl implements AnswerSubmissionJdbctemplateRepository {
  private final JdbcTemplate jdbcTemplate;

  public List<AnswerSubmission> findAnswerMostRecentByRoundId(long roundId) {
    String sql = """
            SELECT s.*
            FROM answer_submission s
            INNER JOIN (
                SELECT user_id, MAX(id) as max_id
                FROM answer_submission
                WHERE round_id = ?
                GROUP BY user_id
            ) latest ON s.user_id = latest.user_id AND s.id = latest.max_id
            WHERE s.round_id = ?
        """;
    return jdbcTemplate.query(
        sql,
        new Long[]{roundId, roundId},
        (resultSet, rowNum) -> AnswerSubmission.createForResult(resultSet));
  }

  @Override
  public Optional<AnswerSubmission> findByRoundIdAndUserId(long roundId, long userId) {
    String sql = """
            SELECT *
            FROM answer_submission s
            WHERE s.round_id = ?
            AND s.user_id = ?
        """;

    try {
      return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{roundId, userId}, new AnswerSubmissionRowMapper()));
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public List<AnswerSubmission> findLatestByRoundIdAndAnswer(long roundId, String answer) {
    String sql = """
            SELECT s.*
            FROM answer_submission s
            WHERE s.round_id = ?
            AND s.answer = ?
        """;
    return jdbcTemplate.query(
            sql,
            (resultSet, rowNum) -> AnswerSubmission.createForResult(resultSet),
            roundId, answer);
  }

  @Override
  public AnswerSubmission saveAnswer(AnswerSubmission answerSubmission) {
    return null;
  }

  private static class AnswerSubmissionRowMapper implements RowMapper<AnswerSubmission> {
    @Override
    public AnswerSubmission mapRow(ResultSet rs, int rowNum) throws SQLException {
      return AnswerSubmission.createForResult(rs);
    }
  }
}
