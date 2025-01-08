package io.gameservice.api.submission.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 7.
 */
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(SubmissionId.class)
public class Submission {

    @Id
    @Column(name = "party_id")
    private Long partyId;

    @Id
    @Column(name = "round_count")
    private Long roundCount;

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "submission_id_generator")
    @GenericGenerator(
            name = "submission_id_generator",
            strategy = "io.gameservice.api.submission.infrastructure.entity.SubmissionIdGenerator"
    )
    @Column(name = "submission_count")
    private Integer submissionCount;

    private Long number;

    @Column(length = 255)
    private String text;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public static SubmissionBuilder defaultBuilder() {
        return Submission.builder();
    }


    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}