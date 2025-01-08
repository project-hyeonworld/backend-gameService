package io.gameservice.api.submission.domain.dto.out;


import io.gameservice.api.submission.domain.dto.in.SubmissionCommand;
import io.gameservice.api.submission.infrastructure.entity.Submission;
import lombok.Builder;
import lombok.Getter;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 7.
 */
@Getter
@Builder
public class FullSubmissionInfo {

    private long id;
    private final long partyId;
    private final long roundCount;
    private final long userId;
    private String text;
    private Long number;

    private static Submission.SubmissionBuilder initializeEntity() {
        return Submission.builder();
    }

    public static Submission toEntity(SubmissionCommand command, long roundCount) {
        return initializeEntity()
                .partyId(command.partyId())
                .roundCount(roundCount)
                .userId(command.userId())
                .text(command.text())
                .number(command.number())
                .build();
    }

    public static FullSubmissionInfo from(Submission submission) {
        return FullSubmissionInfo.builder()
                .text(submission.getText())
                .number(submission.getNumber())
                .partyId(submission.getPartyId())
                .roundCount(submission.getRoundCount())

                .build();
    }

    public static FullSubmissionInfo from(long partyId, Submission submission) {
        return FullSubmissionInfo.builder()
                .partyId(partyId)
                .roundCount(submission.getRoundCount())
                .build();
    }

    public static FullSubmissionInfo from(long partyRoundId, SubmissionCommand command) {
        return FullSubmissionInfo.builder()
                .partyId(command.partyId())
                .roundCount(partyRoundId)
                .build();
    }

    public MiniSubmissionInfo patchUserName(String userName) {
        return MiniSubmissionInfo.from(userId, userName, text, number);
    }
}
