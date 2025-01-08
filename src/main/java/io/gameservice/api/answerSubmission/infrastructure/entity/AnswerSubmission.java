package io.gameservice.api.answerSubmission.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 14.
 */
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "answer_submission",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"round_id", "user_id"})
})
@IdClass(AnswerSubmissionId.class)
public class AnswerSubmission {
  @Id
  @Column(name = "round_id")
  private Long roundId;

  @Id
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "answer")
  private String answer;

  public static AnswerSubmissionBuilder defaultBuilder(){
    return AnswerSubmission.builder();
  }

  public static AnswerSubmission createForResult(ResultSet rs) throws SQLException {
    return defaultBuilder()
        .roundId(rs.getLong("round_id"))
        .userId(rs.getLong("user_id"))
        .answer(rs.getString("answer"))
        .build();
  }
}
