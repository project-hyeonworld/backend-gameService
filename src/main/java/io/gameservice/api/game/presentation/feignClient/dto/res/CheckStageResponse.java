package io.gameservice.api.game.presentation.feignClient.dto.res;

import io.gameservice.api.game.domain.dto.out.CheckInfo;
import io.gameservice.api.game.domain.dto.out.check.SubmissionInfo;
import io.gameservice.api.submission.domain.dto.out.MiniSubmissionInfo;
import io.gameservice.common.mapper.ObjectrMapper;
import java.util.Collections;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 25. 1. 8.
 */
public record CheckStageResponse (
        long userId,
        String name,
        String text,
        long number
){

    public static List<CheckStageResponse> from(CheckInfo checkInfo) {
        if (checkInfo instanceof SubmissionInfo) {
            return ((SubmissionInfo) checkInfo).stream()
                    .map(CheckStageResponse::from)
                    .toList();
        }
        return Collections.singletonList(new CheckStageResponse(0L, null, null, 0L));
    }

    private static CheckStageResponse from(MiniSubmissionInfo submissionInfo) {
        return ObjectrMapper.convert(submissionInfo, CheckStageResponse.class);
    }
}

