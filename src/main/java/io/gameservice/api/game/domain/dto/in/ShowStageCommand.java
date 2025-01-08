package io.gameservice.api.game.domain.dto.in;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 25. 1. 8.
 */
public record ShowStageCommand (
        long gameId,
        long partyId,
        long roundCount,
        long targetId
){

    public static ShowStageCommand from(long gameId, long partyId, long roundCount, long targetId) {
        return new ShowStageCommand(gameId, partyId, roundCount, targetId);
    }
}
