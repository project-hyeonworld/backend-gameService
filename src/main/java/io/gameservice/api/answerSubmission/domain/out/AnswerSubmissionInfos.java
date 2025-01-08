package io.gameservice.api.answerSubmission.domain.out;

import io.gameservice.api.answerSubmission.infrastructure.entity.AnswerSubmission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 16.
 */
public class AnswerSubmissionInfos extends ArrayList<AnswerSubmissionInfo> {

    private AnswerSubmissionInfos(List<AnswerSubmissionInfo> answerSubmissionInfos) {
        super(answerSubmissionInfos != null ? answerSubmissionInfos : Collections.emptyList());
    }

    public static AnswerSubmissionInfos from(List<AnswerSubmission> answerSubmission) {
        return new AnswerSubmissionInfos(answerSubmission.stream()
                .map(AnswerSubmissionInfo::from)
                .toList());
    }

    public List<Long> getWinnerIds(String answer) {
        return this.stream()
                .filter(answerSubmissionInfo -> answerSubmissionInfo.matchAnswer(answer))
                .map(AnswerSubmissionInfo::getId)
                .collect(Collectors.toList());
    }

    public List<Long> getUserIds() {
        return stream()
                .map(AnswerSubmissionInfo::getUserId)
                .toList();

    }
}