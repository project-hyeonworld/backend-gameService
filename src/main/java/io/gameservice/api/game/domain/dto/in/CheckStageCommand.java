package io.gameservice.api.game.domain.dto.in;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 25. 1. 8.
 */
public record CheckStageCommand (
    long gameId,
    long partyId,
    long roundCount
) {

    public static CheckStageCommand from (long gameId, long partyId, long roundCount) {
        return new CheckStageCommand(gameId, partyId, roundCount);
    }

}
