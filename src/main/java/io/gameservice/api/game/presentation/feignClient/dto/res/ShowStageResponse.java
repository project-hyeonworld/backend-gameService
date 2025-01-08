package io.gameservice.api.game.presentation.feignClient.dto.res;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 25. 1. 8.
 */
public record ShowStageResponse (
        String content
){

    public static ShowStageResponse from(String content) {
        return new ShowStageResponse(content);
    }
}
