package io.gameservice.api.game.domain.dto.out;

import io.gameservice.api.round.application.dto.in.SubmitCommand;
import lombok.Builder;
import lombok.Getter;

/**
* @author : hyeonwoody@gmail.com
* @since : 25. 1. 7.
*/
@Getter
@Builder
public class SubmissionInfo {

    private long id;
    private final long partyId;
    private final long partyRoundId;
    private final long userId;
    private String text;
    private Long number;

    public static SubmissionInfo from(long partyRoundId, SubmitCommand command) {
        return SubmissionInfo.builder()
                .partyId(command.partyId())
                .partyRoundId(partyRoundId)
                .build();
    }
}
