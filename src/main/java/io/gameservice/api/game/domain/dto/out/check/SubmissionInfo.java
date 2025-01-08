package io.gameservice.api.game.domain.dto.out.check;

import io.gameservice.api.game.domain.dto.out.CheckInfo;
import io.gameservice.api.submission.domain.dto.out.MiniSubmissionInfo;
import io.gameservice.api.submission.domain.dto.out.MiniSubmissionInfos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 25. 1. 7.
 */
public class SubmissionInfo extends ArrayList<MiniSubmissionInfo> implements CheckInfo {

    private SubmissionInfo(List<MiniSubmissionInfo> roundSubmissionInfos) {
        super(roundSubmissionInfos != null ? roundSubmissionInfos : Collections.emptyList());
    }

    public static SubmissionInfo from(MiniSubmissionInfos minisubmissionInfos) {
        return new SubmissionInfo(minisubmissionInfos);
    }
}
