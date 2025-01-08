package io.gameservice.api.game.domain.dto.in;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 25. 1. 8.
 */
public record CheckConfirmCommand(
        long partyId,
        long partyRoundId,
        long gameId,
        long targetId
) {

}

