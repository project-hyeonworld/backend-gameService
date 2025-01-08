package io.gameservice.api.submission.domain.dto.out;

import io.gameservice.api.submission.infrastructure.entity.Submission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 8.
 */
public class MiniSubmissionInfos extends ArrayList<MiniSubmissionInfo> {

    private MiniSubmissionInfos(List<MiniSubmissionInfo> roundMiniSubmissionInfos) {
        super(roundMiniSubmissionInfos != null ? roundMiniSubmissionInfos : Collections.emptyList());
    }

    public static MiniSubmissionInfos from(List<Submission> submission) {
        return new MiniSubmissionInfos(submission.stream()
                .map(MiniSubmissionInfo::from)
                .toList());
    }
}
