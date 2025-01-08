package io.gameservice.api.game.domain.dto.in;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 25. 1. 8.
 */
public record ResultStageCommand(
        long gameId,
        long partyId,
        long roundCount,
        long targetId
) {

    public static ResultStageCommand from (long gameId, long partyId, long roundCount, long targetId) {
        return new ResultStageCommand(gameId, partyId, roundCount, targetId);
    }
}
