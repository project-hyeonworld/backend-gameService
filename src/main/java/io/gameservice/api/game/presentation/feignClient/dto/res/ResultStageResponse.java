package io.gameservice.api.game.presentation.feignClient.dto.res;

import io.gameservice.api.game.domain.dto.out.ResultInfo;
import io.gameservice.common.dto.NameDtos;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 25. 1. 8.
 */
public record ResultStageResponse (
        String answer,
        NameDtos winners
){

    public static ResultStageResponse from(ResultInfo resultInfo) {
        return new ResultStageResponse(resultInfo.answer(), resultInfo.nameDtos());
    }
}
